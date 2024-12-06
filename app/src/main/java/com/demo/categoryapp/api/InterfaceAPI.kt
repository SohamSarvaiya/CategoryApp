package com.demo.categoryapp.api

import com.demo.categoryapp.models.CategoryListItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface InterfaceAPI {

    @GET("/v3/b/66bd89d9ad19ca34f89657a2?meta=false")
    suspend fun getCatList(@Header("X-JSON-Path") category: String) : Response<List<CategoryListItem>>

    @GET("/v3/b/66bd89d9ad19ca34f89657a2?meta=false")
    @Headers("X-JSON-Path: tweets..category")
    suspend fun getCategories(): Response<List<String>>
}