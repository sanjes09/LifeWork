<?php

use Illuminate\Support\Facades\Schema;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreatePif1Table extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('pif_1', function (Blueprint $table) {
            $table->increments('id');
            
        // 1 Contact details & declaration
            $table->string('suppliers_product_name')->nullable();
            $table->string('suppliers_product_code')->nullable();
            $table->string('bar_code_unit_gtin')->nullable();
            $table->unsignedInteger('specify_country_imported_into_1')->nullable(); // FG Countries
            $table->unsignedInteger('specify_country_imported_into_2')->nullable(); // FG Countries
            $table->unsignedInteger('specify_country_exported_from_1')->nullable(); // FG Countries
            $table->unsignedInteger('specify_country_exported_from_2')->nullable(); // FG Countries
            $table->string('specify_import_tariff_into')->nullable();
            
        // 1.1 Supplier information
            $table->string('company_name')->nullable();
            $table->string('business_number')->nullable();
            $table->string('trading_name')->nullable();
            $table->string('business_address_number')->nullable();
            $table->string('business_address_street')->nullable();
            $table->string('business_address_suburb')->nullable();
            $table->string('business_address_state')->nullable();
            $table->unsignedInteger('business_address_country_id')->nullable(); // FG Countries
            $table->string('business_address_postcode')->nullable();
            $table->string('postal_address')->nullable();
            $table->string('postal_address_suburb')->nullable(); 
            $table->string('postal_address_city')->nullable();
            $table->unsignedInteger('postal_address_country_id')->nullable(); // FG Countries
            $table->string('postal_address_postcode')->nullable();
            $table->string('key_contact_name')->nullable();
            $table->string('key_contact_position_title')->nullable();
            $table->string('key_contact_email')->nullable();
            $table->string('key_contact_phone')->nullable();
            $table->string('key_contact_fax')->nullable();
            $table->string('key_contact_dateform_completed')->nullable();
            $table->string('key_contact_issue_date')->nullable();
            $table->string('key_contact_issue_document_no')->nullable();
            $table->string('key_contact_issue_number')->nullable();

        // 1.2 Manufacturing information
            // Info in table pif_1_2_manufacturing_information

        // 1.3 Contact details technical & allergen information
            $table->string('technical_allergen_name')->nullable();
            $table->string('technical_allergen_job_title')->nullable();
            $table->string('technical_allergen_email')->nullable();
            $table->string('technical_allergen_tel_work')->nullable();
            $table->string('technical_allergen_tel_mobile')->nullable();

        // 1.4 Supplier declaration and warranty
            $table->string('declaration_company_name')->nullable();
            $table->string('declaration_name')->nullable();
            $table->string('declaration_job_title')->nullable();
            $table->string('declaration_authorised_signature')->nullable();
            $table->date('declaration_date_of_authorization')->nullable();

        // 1.5 Customers details
            $table->string('customer_company_name')->nullable();
            $table->string('customer_address_number')->nullable();
            $table->string('customer_address_street')->nullable();
            $table->string('customer_address_suburb')->nullable();
            $table->string('customer_address_city')->nullable();
            $table->unsignedInteger('customer_country_id')->nullable(); // FG Countries
            $table->string('customer_address_postcode')->nullable();
            $table->string('customer_contact_name')->nullable();
            $table->string('customer_product_name')->nullable(); 
            $table->string('customer_product_code')->nullable();

            $table->string('customer_int_product_code_desc')->nullable();
            $table->string('customer_version_no')->nullable();
            $table->string('customer_reason_for_update')->nullable();
            $table->string('customer_received_and_reviewed_by')->nullable();
            $table->boolean('customer_approved')->nullable();
            $table->date('customer_approved_date')->nullable();
            $table->string('customer_signature')->nullable();

            // 1.7 Checlist and attachments
            $table->boolean('page2_signed_dated')->nullable();
            $table->boolean('current_certificates_attached')->nullable();
            $table->boolean('supplier_for_analysis')->nullable();
            $table->boolean('other_associated_documents')->nullable();

            $table->boolean('help_is_needed')->nullable();

            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('pif_1');
    }
}
