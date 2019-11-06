package com.example.book

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.*

interface StatusAPI  {
    @GET("allborrow")
    fun retrieveStatus(): Call<List<Status>>

    @GET("borrow/{std_id}")
    fun retrieveStatus(
        @Path("std_id")std_id: String): Call<Status>


    @FormUrlEncoded
    @PUT("borrow/{Book_id}")
    fun updateStatus(
        @Path("std_id")  std_id : String,
        @Field("std_name") std_name: String,
        @Field("Book_name") Book_name: String,
        @Field("Date_borrow") Date_borrow: String,
        @Field("Date_return") Date_return: String ) : Call<Status>

    @FormUrlEncoded
    @POST("borrow")
    fun insertStatus(
        @Field("std_id") std_id: String,
        @Field("std_name") std_name: String,
        @Field("Book_name") Book_name: String,
        @Field("Date_borrow") Date_borrow: String,
        @Field("Date_return") Date_return: String ) : Call<Status>

    /*companion object {
        fun create(): StudentAPI{
            val stdClient : StudentAPI = Retrofit.Builder
        }
    }*/
    @FormUrlEncoded
    @DELETE("borrow/{std_id}")
    fun deleteStatus(
        @Path("std_id") std_id: String): Call<Status>
    companion object{
        fun create(): StatusAPI {
            val borrowClient : StatusAPI = Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(StatusAPI ::class.java)
            return borrowClient
        }
    }}