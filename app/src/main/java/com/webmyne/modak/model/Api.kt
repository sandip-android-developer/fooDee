package com.webmyne.modak.model

import com.webmyne.modak.model.ResponsPojo.GetCountryResponse
import com.webmyne.modak.model.ResponsPojo.GetStateResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface Api {
     @GET("api.php")
    fun getCountry(@Query("type") country:String): Call<GetCountryResponse>

    @GET("api.php")
    fun getState(@Query("type") country:String,@Query("countryId") countryId:String): Call<GetStateResponse>
}


