package com.example.book

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*


interface StudentAPI {
    @GET("allstd")
    fun retrieveStudent(): Call<List<Student>>

    @GET("std/{Book_name}")
    fun retrieveStudent(
        @Path("Book_name")Book_id: String): Call<Student>


    @FormUrlEncoded
    @PUT("std/{Book_id}")
    fun updateStudent(
        @Path("Book_id")  Book_id : String,
        @Field("Book_name") Book_name: String,
        @Field("Writer_name") Writer_name: String,
        @Field("Publisher_name") Publisher_name: String,
        @Field("img") img: String ) : Call<Student>

    @FormUrlEncoded
    @POST("std")
    fun insertStd(
        @Field("Book_id") Book_id: String,
        @Field("Book_name") Book_name: String,
        @Field("Writer_name") Writer_name: String,
        @Field("Publisher_name") Publisher_name: String,
        @Field("img") img: String ) : Call<Student>

    /*companion object {
        fun create(): StudentAPI{
            val stdClient : StudentAPI = Retrofit.Builder
        }
    }*/
    @FormUrlEncoded
    @DELETE("std/{Book_id}")
    fun deleteStudent(
        @Path("Book_id") std_id: String): Call<Student>
    companion object{
        fun create(): StudentAPI {
            val stdClient : StudentAPI = Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(StudentAPI ::class.java)
            return stdClient
        }
    }}

/*interface StudentAPI {
    @GET("allstd")
    fun retrieveStudent(): Call<List<Student>>

    @GET("std/{Book_name}")
    fun retrieveStudent(
        @Path("Book_name")Book_id: String): Call<Student>


    @FormUrlEncoded
    @PUT("std/{Book_id}")
    fun updateStudent(
        @Path("Book_id")  Book_id : String,
        @Field("Book_name") Book_name: String,
        @Field("Writer_name") Writer_name: String,
        @Field("Publisher_name") Publisher_name: String,
        @Field("img") img: String ) : Call<Student>


    //@FormUrlEncoded
    @POST("std")
    fun insertStd(
        @Field("Book_id") Book_id: String,
        @Field("Book_name") Book_name: String,
        @Field("Writer_name") Writer_name: String,
        @Field("Publisher_name") Publisher_name: String,
        @Field("img") img: String ) : Call<Student>


    @DELETE("std/{Book_id}")
    fun deleteStudent(
        @Path("Book_id") Book_id: String): Call<Student>*/

    /*companion object{
        fun create(): StudentAPI {
            val stdClient : StudentAPI = Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(StudentAPI ::class.java)
            return stdClient
        }


    }
}*/
/*companion object {
      fun create(): StudentAPI{
          val stdClient : StudentAPI = Retrofit.Builder
      }
  }*/