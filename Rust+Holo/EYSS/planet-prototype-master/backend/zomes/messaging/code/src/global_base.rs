#![feature(try_from)]
use hdk::{
    entry_definition::ValidatingEntryType,
    error::ZomeApiResult,
};
use hdk::holochain_core_types::{
    cas::content::Address,
    entry::Entry,
    dna::entry_types::Sharing,
    error::HolochainError,
    json::JsonString,
    validation::EntryValidationData
};

// see https://developer.holochain.org/api/0.0.15-alpha1/hdk/ for info on using the hdk library


#[derive(Serialize, Deserialize, Debug, DefaultJson,Clone)]
pub struct Base {
    seed: String,
}

pub fn get_base_entry() -> Base{
    Base {
        seed:String::from("horse")
    }
}
pub fn get_base_hash()  -> Address{
    let entry = Entry::App("global_base".into(),get_base_entry().into());
    hdk::commit_entry(&entry).unwrap()
}
pub fn commit_base() {
    let entry = Entry::App("global_base".into(),get_base_entry().into());
    let address = hdk::commit_entry(&entry);
}
pub fn definition() -> ValidatingEntryType {
    entry!(
        name: "global_base",
        description: "global base to link users and channels",
        sharing: Sharing::Public,
        validation_package: || {
            hdk::ValidationPackageDefinition::Entry
        },
        validation: | _validation_data: hdk::EntryValidationData<Base>| {
            Ok(())
        },
        links: [
            to!(
                "channel",
                link_type: "public channels for all",
                validation_package: || {
                    hdk::ValidationPackageDefinition::Entry
                },
                validation: |_validation_data: hdk::LinkValidationData| {
                    Ok(())
                }
            ),
            to!(
                "user",
                link_type: "public for all",
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