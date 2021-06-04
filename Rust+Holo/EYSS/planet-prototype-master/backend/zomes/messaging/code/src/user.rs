#![feature(try_from)]
use hdk::{
    entry_definition::ValidatingEntryType,
    error::ZomeApiResult,
    error::ZomeApiError,
};
use hdk::holochain_core_types::{
    cas::content::Address,
    entry::Entry,
    dna::entry_types::Sharing,
    error::HolochainError,
    json::JsonString,
    validation::EntryValidationData
};
use serde_json::json;

// see https://developer.holochain.org/api/0.0.15-alpha1/hdk/ for info on using the hdk library


#[derive(Serialize, Deserialize, Debug, DefaultJson,Clone)]
pub struct User {
    username : String,
    address : String
}

use crate::global_base;
use crate::utils;

pub fn handle_create_user( username : String ) -> ZomeApiResult<Address> {
    if check_register() {
        return Err(ZomeApiError::Internal(String::from("Already registered")));
    }
    let user = User{
        username,
        address : hdk::AGENT_ADDRESS.to_string()
    };
    let entry = Entry::App("user".into(), user.into());
    let address = hdk::commit_entry(&entry)?;
    hdk::api::link_entries(&global_base::get_base_hash(), &address.clone(), "public for all","")?;
    match hdk::link_entries(&hdk::AGENT_ADDRESS, &address, "personal_link","") {
        Ok(_)=>Ok(address),
        Err(_) => Err(ZomeApiError::Internal("Entry Linking failed".to_string())),
    }
}

fn check_register() -> bool {
    match hdk::get_links(&hdk::AGENT_ADDRESS,  Some("personal_link".to_string()),Some("".to_string())) {
        Ok(result_vec) => result_vec.addresses().len() != 0,
        Err(error) => {
            false
        }
    }
}

pub fn handle_check_register() ->  ZomeApiResult<serde_json::Value>{
    Ok(
        if check_register() {
            let addr = &hdk::get_links(&hdk::AGENT_ADDRESS, Some("personal_link".to_string()),Some("".to_string())).unwrap().addresses()[0];
            json!({
                "registered" : true,
                "me" : &hdk::get_entry(&addr.to_owned()).unwrap().unwrap()
            })
        } else {
            json!({
                "registered" : false
            })
        }
    )
}

pub fn handle_get_all_users() -> ZomeApiResult<utils::GetLinksLoadResult<User>> {
    utils::get_links_and_load_type(&global_base::get_base_hash(), "public for all".to_string())
}

pub fn definition() -> ValidatingEntryType {
    entry!(
        name: "user",
        description: "user entry",
        sharing: Sharing::Public,
        validation_package: || {
            hdk::ValidationPackageDefinition::Entry
        },
        validation: | _validation_data: hdk::EntryValidationData<User>| {
            Ok(())
        },
        links: [
            from!(
                "global_base",
                link_type: "public for all",
                validation_package: || {
                    hdk::ValidationPackageDefinition::Entry
                },
                validation: |_validation_data: hdk::LinkValidationData| {
                    Ok(())
                }
            ),
            from!(
                "%agent_id",
                link_type: "personal_link",
                validation_package: || {
                    hdk::ValidationPackageDefinition::Entry
                },
                validation: |_validation_data: hdk::LinkValidationData| {
                    Ok(())
                }
            )
        ]
    )
}