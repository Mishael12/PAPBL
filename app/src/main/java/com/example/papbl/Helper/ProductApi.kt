package com.example.papbl.Helper

import retrofit2.Response
import retrofit2.http.GET

interface ProductApi {
    @GET("products")
    suspend fun getProducts(): Response<ProductList>


}