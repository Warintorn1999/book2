package com.example.book

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Status (

    @Expose
    @SerializedName( "std_id") val std_id: String,

    @Expose
    @SerializedName( "std_name") val std_name: String,

    @Expose
    @SerializedName( "Book_name") val Book_name: String,

    @Expose
    @SerializedName( "Date_borrow") val Date_borrow: String,

    @Expose
    @SerializedName( "Date_return") val Date_return: String){}
