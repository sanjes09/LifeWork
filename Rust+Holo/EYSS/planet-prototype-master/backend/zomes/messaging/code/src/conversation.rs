#![feature(try_from)]
use hdk::{
    entry_definition::ValidatingEntryType,
    error::ZomeApiResult,
    DNA_ADDRESS,
};
use hdk::holochain_core_types::{
    cas::content::Address,
    entry::Entry,
    dna::entry_types::Sharing,
    error::HolochainError,
    json::JsonString,
    validation::EntryValidationData,
    json::RawString,
};

use crate::global_base;
use crate::utils;


#[derive(Serialize, Deserialize, Debug, DefaultJson,Clone)]
pub struct Conversation {
    p1: Address,
    p2: Address,
}

pub fn handle_create_conversation( buddy : Address ) -> ZomeApiResult<Address> {
    let entry = Conversation{
        p1 : buddy.clone(),
        p2 : Address::from(hdk::AGENT_ADDRESS.to_string())
    };
    let entry = Entry::App("conversation".into(), RawString::from("conversation").into());
    let address = hdk::commit_entry(&entry)?;
    hdk::link_entries(&buddy, &address, "belongs_to","")?;
    hdk::link_entries(&hdk::AGENT_ADDRESS,&address,"belongs_to","")?;
    Ok(address)
}
pub fn handle_get_my_conversations() -> ZomeApiResult<utils::GetLinksLoadResult<Conversation>> {
    utils::get_links_and_load_type(&hdk::AGENT_ADDRESS, "belongs_to".to_string())
}
pub fn definition() -> ValidatingEntryType {
    entry!(
        name: "conversation",
        description: "comunication mechanism in order to create private conversations",
        sharing: Sharing::Public,
        validation_package: || {
            hdk::ValidationPackageDefinition::Entry
        },
        validation: | _validation_data: hdk::EntryValidationData<Conversation>| {
            Ok(())
        },
        links: [
            from!(
                "%agent_id",
                link_type: "belongs_to",
                validation_package: || {
                    hdk::ValidationPackageDefinition::Entry
                },
                validation: |_validation_data: hdk::LinkValidationData| {
                    Ok(())
                }
            ),
            to!(
                "message",
                link_type: "has_message",
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