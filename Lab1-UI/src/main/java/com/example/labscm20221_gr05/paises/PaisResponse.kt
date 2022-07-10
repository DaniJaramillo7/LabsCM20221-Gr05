package com.example.labscm20221_gr05.paises

import com.google.gson.annotations.SerializedName

data class PaisResponse(
    @SerializedName("country_name") var countryName: String,
    @SerializedName("country_short_name") var shortName: String,
    @SerializedName("country_phone_code") var countryPhoneCode: String
) {
}