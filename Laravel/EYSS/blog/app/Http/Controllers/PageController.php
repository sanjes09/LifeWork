<?php

namespace App\Http\Controllers;


use Illuminate\Http\Request;
use DB;
use Validator;
use App\Pif1;
use App\Pif1_2;

class PageController extends Controller{

	public function home(){
		return view("welcome");
	}

	private function validateRequest(Request $request){

		$rules = array(
			'suppliers_product_name' => 'string',
			'suppliers_product_code' => 'string',
			'bar_code_unit_gtin' => 'string',
			'specify_country_imported_into_1' => 'integer',
			'specify_country_imported_into_2' => 'integer',
			"specify_country_exported_from_1" => 'integer',
			"specify_country_exported_from_2" => 'integer',
			"specify_import_tariff_into" => 'string',
			"company_name" => 'string',
			"business_number" => 'string',
			"trading_name" => 'string',
			"business_address_number" => 'string',
			"business_address_street" => 'string',
			"business_address_suburb" => 'string',
			"business_address_state" => 'string',
			"business_address_country_id" => 'integer',
			"business_address_postcode" => 'string',
			"postal_address" => 'string',
			"postal_address_suburb" => 'string',
			"postal_address_city" => 'string',
			"postal_address_country_id" => 'integer',
			"postal_address_postcode" => 'string',
			"key_contact_name" => 'string',
			"key_contact_position_title" => 'string',
			"key_contact_email" => 'string',
			"key_contact_phone" => 'string',
			"key_contact_fax" => 'string',
			"key_contact_dateform_completed" => 'string',
			"key_contact_issue_date" => 'string',
			"key_contact_issue_document_no" => 'string',
			"key_contact_issue_number" => 'string',
			"technical_allergen_name" => 'string',
			"technical_allergen_job_title" => 'string',
			"technical_allergen_email" => 'string',
			"technical_allergen_tel_work" => 'string',
			"technical_allergen_tel_mobile" => 'string',
			"declaration_company_name" => 'string',
			"declaration_name" => 'string',
			"declaration_job_title" => 'string',
			"declaration_authorised_signature" => 'string',
			"declaration_date_of_authorization" => 'date',
			"customer_company_name" => 'string',
			"customer_address_number" => 'string',
			"customer_address_street" => 'string',
			"customer_address_suburb" => 'string',
			"customer_address_city" => 'string',
			"customer_country_id" => 'integer',
			"customer_address_postcode" => 'string',
			"customer_contact_name" => 'string',
			"customer_product_name" => 'string',
			"customer_product_code" => 'string',
			"customer_int_product_code_desc" => 'string',
			"customer_version_no" => 'string',
			"customer_reason_for_update" => 'string',
			"customer_received_and_reviewed_by" => 'string',
			"customer_approved" => 'integer',
			"customer_approved_date" => 'date',
			"customer_signature" => 'string',
			"page2_signed_dated" => 'integer',
			"current_certificates_attached" => 'integer',
			"supplier_for_analysis" => 'integer',
			"other_associated_documents" => 'integer',
			"help_is_needed" => 'integer',
			"manufacturing_company.*.manufacturing_company_name" => 'string',
			"manufacturing_company.*.manufacturing_address_number" => 'string',
			"manufacturing_company.*.manufacturing_address_street" => 'string',
			"manufacturing_company.*.manufacturing_address_suburb" => 'string',
			"manufacturing_company.*.manufacturing_address_state" => 'string',
			"manufacturing_company.*.manufacturing_address_postcode" => 'integer',
			"manufacturing_company.*.country_id" => 'integer'
		);

		return Validator::make($request->all(),$rules);
	}

	private function guardarPif1(Pif1 $form, Request $request){
		$form->suppliers_product_name = $request->input('suppliers_product_name');
		$form->suppliers_product_code = $request->input('suppliers_product_code');
		$form->bar_code_unit_gtin = $request->input('bar_code_unit_gtin');
		$form->specify_country_imported_into_1 = $request->input('specify_country_imported_into_1');
		$form->specify_country_imported_into_2 = $request->input('specify_country_imported_into_2');
		$form->specify_country_exported_from_1 = $request->input('specify_country_exported_from_1');
		$form->specify_country_exported_from_2 = $request->input('specify_country_exported_from_2');
		$form->specify_import_tariff_into = $request->input('specify_import_tariff_into');
		$form->company_name = $request->input('company_name');
		$form->business_number = $request->input('business_number');
		$form->trading_name = $request->input('trading_name');
		$form->business_address_number = $request->input('business_address_number');
		$form->business_address_street = $request->input('business_address_street');
		$form->business_address_suburb = $request->input('business_address_suburb');
		$form->business_address_state = $request->input('business_address_state');
		$form->business_address_country_id = $request->input('business_address_country_id');
		$form->business_address_postcode = $request->input('business_address_postcode');
		$form->postal_address = $request->input('postal_address');
		$form->postal_address_suburb = $request->input('postal_address_suburb');
		$form->postal_address_city = $request->input('postal_address_city');
		$form->postal_address_country_id = $request->input('postal_address_country_id');
		$form->postal_address_postcode = $request->input('postal_address_postcode');
		$form->key_contact_name = $request->input('key_contact_name');
		$form->key_contact_position_title = $request->input('key_contact_position_title');
		$form->key_contact_email = $request->input('key_contact_email');
		$form->key_contact_phone = $request->input('key_contact_phone');
		$form->key_contact_fax = $request->input('key_contact_fax');
		$form->key_contact_dateform_completed = $request->input('key_contact_dateform_completed');
		$form->key_contact_issue_date = $request->input('key_contact_issue_date');
		$form->key_contact_issue_document_no = $request->input('key_contact_issue_document_no');
		$form->key_contact_issue_number = $request->input('key_contact_issue_number');
		$form->technical_allergen_name = $request->input('technical_allergen_name');
		$form->technical_allergen_job_title = $request->input('technical_allergen_job_title');
		$form->technical_allergen_email = $request->input('technical_allergen_email');
		$form->technical_allergen_tel_work = $request->input('technical_allergen_tel_work');
		$form->technical_allergen_tel_mobile = $request->input('technical_allergen_tel_mobile');
		$form->declaration_company_name = $request->input('declaration_company_name');
		$form->declaration_name = $request->input('declaration_name');
		$form->declaration_job_title = $request->input('declaration_job_title');
		$form->declaration_authorised_signature = $request->input('declaration_authorised_signature');
		$form->declaration_date_of_authorization = $request->input('declaration_date_of_authorization');
		$form->customer_company_name = $request->input('customer_company_name');
		$form->customer_address_number = $request->input('customer_address_number');
		$form->customer_address_street = $request->input('customer_address_street');
		$form->customer_address_suburb = $request->input('customer_address_suburb');
		$form->customer_address_city = $request->input('customer_address_city');
		$form->customer_country_id = $request->input('customer_country_id');
		$form->customer_address_postcode = $request->input('customer_address_postcode');
		$form->customer_contact_name = $request->input('customer_contact_name');
		$form->customer_product_name = $request->input('customer_product_name');
		$form->customer_product_code = $request->input('customer_product_code');
		$form->customer_int_product_code_desc = $request->input('customer_int_product_code_desc');
		$form->customer_version_no = $request->input('customer_version_no');
		$form->customer_reason_for_update = $request->input('customer_reason_for_update');
		$form->customer_received_and_reviewed_by = $request->input('customer_received_and_reviewed_by');
		$form->customer_approved = $request->input('customer_approved');
		$form->customer_approved_date = $request->input('customer_approved_date');
		$form->customer_signature = $request->input('customer_signature');
		$form->page2_signed_dated = $request->input('page2_signed_dated');
		$form->current_certificates_attached = $request->input('current_certificates_attached');
		$form->supplier_for_analysis = $request->input('supplier_for_analysis');
		$form->other_associated_documents = $request->input('other_associated_documents');
		$form->help_is_needed = $request->input('help_is_needed');
		return $form;
	}

	private function guardarPif1_2(Pif1_2 $form2, Request $request){
		
		for($i=0;$i<3;$i++){
			$form3 = new Pif1_2;
			$form3->pif_1_id = $form2->pif_1_id;
			$form3->manufacturing_company_name = $request->input("manufacturing_company")[$i]['manufacturing_company_name'];
			$form3->manufacturing_address_number = $request->input("manufacturing_company")[$i]['manufacturing_address_number'];
			$form3->manufacturing_address_street = $request->input("manufacturing_company")[$i]['manufacturing_address_street'];
			$form3->manufacturing_address_suburb = $request->input("manufacturing_company")[$i]['manufacturing_address_suburb'];
			$form3->manufacturing_address_state = $request->input("manufacturing_company")[$i]['manufacturing_address_state'];
			$form3->manufacturing_address_postcode = $request->input("manufacturing_company")[$i]['manufacturing_address_postcode'];
			$form3->country_id = $request->input("manufacturing_company")[$i]['country_id'];
			$form3->save();
		}
	}

	public function guardar(Request $request){
		
		$validator = $this->validateRequest($request);
				
		if($validator->fails()){
			$messages=$validator->messages();
			$errors=$messages->all();
			return response()->json([
			'errors' => $errors,
			'success' => false
			], 400);
		}else{
			//GUARDA PIF
			$form = new Pif1;
			$form->save();
			$form = $this->guardarPif1($form,$request);
			$form->save();
			//GUARDA PIF1-2
			$form2 = new Pif1_2;
			$form2->pif_1_id = $form->id;
			$form2 = $this->guardarPif1_2($form2,$request);
		}
	}
}
