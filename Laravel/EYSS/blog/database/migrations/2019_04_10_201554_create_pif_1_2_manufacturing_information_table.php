<?php

use Illuminate\Support\Facades\Schema;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreatePif12ManufacturingInformationTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('pif_1_2_manufacturing_information', function (Blueprint $table) {
            $table->increments('id');
            $table->string('manufacturing_company_name')->nullable();
            $table->string('manufacturing_address_number')->nullable();
            $table->string('manufacturing_address_street')->nullable();
            $table->string('manufacturing_address_suburb')->nullable();
            $table->string('manufacturing_address_state')->nullable();
            $table->integer('manufacturing_address_postcode')->nullable();
            $table->unsignedInteger('country_id')->nullable(); // FG Countries
            $table->unsignedInteger('pif_1_id'); // FG pif_1
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
        Schema::dropIfExists('pif_1_2_manufacturing_information');
    }
}
