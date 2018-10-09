package com.demo.shaadi.datamodel


import com.google.gson.annotations.SerializedName


class Dob {

    @SerializedName("date")
    var date: String? = null

    @SerializedName("age")
    var age: String? = null
}