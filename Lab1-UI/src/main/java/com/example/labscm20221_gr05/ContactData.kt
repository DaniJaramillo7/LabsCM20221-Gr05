package com.example.labscm20221_gr05

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import androidx.core.widget.doOnTextChanged
import java.util.*

class ContactData : AppCompatActivity() {

    private lateinit var phoneInput: EditText
    private lateinit var addressInput: EditText
    private lateinit var emailInput: EditText
    private lateinit var countryInput: EditText
    private lateinit var cityInput: EditText
    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_data)

        this.initializeInputs()
        this.listCountries()
        this.initializeCountryInput()
        this.initializeButton()
        this.initializeEmailInput()
        this.initializePhoneInput()


    }

    private fun initializeInputs() {
        this.phoneInput = findViewById<EditText>(R.id.input_phone)
        this.addressInput = findViewById<EditText>(R.id.input_address)
        this.emailInput = findViewById<EditText>(R.id.input_email)
        this.countryInput = findViewById<AutoCompleteTextView>(R.id.input_country)
        this.cityInput = findViewById<AutoCompleteTextView>(R.id.input_city)
        this.button = findViewById<Button>(R.id.nextContact)
    }

    private fun onButtonContactNextTap() {
        val phone = this.phoneInput.text.toString()
        val address = this.addressInput.text.toString()
        val email = this.emailInput.text.toString()
        val country = this.countryInput.text.toString()
        val city = this.cityInput.text.toString()
        Log.d("Data", "Información de contacto")
        Log.d("Data", "Telefono: $phone")
        if (address != null) Log.d("Data", "Dirección $address")
        Log.d("Data", "Email: $email")
        Log.d("Data", "País: $country")
        if (city != null) Log.d("Data", "Ciudad: $city")
    }

    private fun initializeButton() {
        this.button.isEnabled = false
        this.button.setOnClickListener() {
            onButtonContactNextTap();
        }

    }

    private fun listCountries() {
        val conutryTexView =
            findViewById<AutoCompleteTextView>(R.id.input_country) as AutoCompleteTextView
        val countries: Array<out String> = resources.getStringArray(R.array.countries)
        ArrayAdapter<String>(
            this,
            android.R.layout.simple_list_item_1,
            countries
        ).also { arrayAdapter ->
            conutryTexView.setAdapter(arrayAdapter)
        }
    }

    private fun initializeCountryInput() {
        this.countryInput
            .doOnTextChanged { text, start, before, count ->
                listCities(text.toString().uppercase(Locale.ROOT))
                evaluateFormIsValid()
            }
    }

    private fun initializeEmailInput() {
        this.emailInput
            .doOnTextChanged { text, start, before, count ->
                evaluateFormIsValid()
            }
    }

    private fun initializePhoneInput() {
        this.phoneInput
            .doOnTextChanged { text, start, before, count ->
                evaluateFormIsValid()
            }
    }


    private fun listCities(country: String) {

        val cityTexView =
            findViewById<AutoCompleteTextView>(R.id.input_city) as AutoCompleteTextView
        val cities: Array<out String> = resources.getStringArray(R.array.cities)
        if (country.equals("COLOMBIA")) {
            ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                cities
            ).also { arrayAdapter ->
                cityTexView.setAdapter(arrayAdapter)
            }
        } else {
            cityTexView.setAdapter(null)
        }

    }

    private fun evaluateFormIsValid() {
        val phoneIsValid = this.phoneInput.text.toString().trim().isNotEmpty()
        val emailIsValid = this.emailInput.text.toString().trim().isNotEmpty()
        val countrylIsValid = this.countryInput.text.toString().trim().isNotEmpty()
        var formIsValid = phoneIsValid && emailIsValid && countrylIsValid
        this.button.isEnabled = formIsValid
    }

}