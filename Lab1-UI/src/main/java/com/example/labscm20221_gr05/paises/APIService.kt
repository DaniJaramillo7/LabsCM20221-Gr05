package com.example.labscm20221_gr05.paises

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Url

interface APIService {

    @GET
    @Headers(
        "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjp7InVzZXJfZW1haWwiOiJsdWlzOTgwOTE4QG91dGxvb2suZXMiLCJhcGlfdG9rZW4iOiJKV3B5R1IwZk5rQnJzaUtoaGt3UUdpaUlUOTFwOF82dzFCLWdrTXpTdVpDcG14anE5N2dUWml1cTRLcWdiNVZ3Y05BIn0sImV4cCI6MTY1NzQxNDEyM30.5Tk1tICKFaKwjr_GvgL0ibsFHMGop2m8h2HKWj1_kBc",
        "Accept:application/json"
    )
    fun getAllPaises(@Url url:String): Response<PaisResponse>
}