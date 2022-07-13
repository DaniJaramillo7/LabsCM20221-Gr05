package com.example.labscm20221_gr05

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.EditText
import java.util.*

class ContactData : AppCompatActivity() {

    //private lateinit var citiesEditText: EditText;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_data)
        this.listCountries()
        this.setCities()

    }

    private fun listCountries(){
        val conutryTexView = findViewById<AutoCompleteTextView>(R.id.input_country) as AutoCompleteTextView
        val countries: Array<out String> = resources.getStringArray(R.array.countries)
        ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,countries).also { arrayAdapter ->
            conutryTexView.setAdapter(arrayAdapter)
        }
    }

    private fun setCities(){
        var countriesEditText:EditText = findViewById(R.id.input_country)
        countriesEditText.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {

                    listCities(countriesEditText.text.toString().uppercase(Locale.ROOT))


            }

        })
    }


    private fun listCities(country: String) {

            val cityTexView =
                findViewById<AutoCompleteTextView>(R.id.input_city) as AutoCompleteTextView
            val cities: Array<out String> = resources.getStringArray(R.array.cities)
        if(country.equals("COLOMBIA")) {
            ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                cities
            ).also { arrayAdapter ->
                cityTexView.setAdapter(arrayAdapter)
            }
        } else{
            cityTexView.setAdapter(null)
        }

    }

}