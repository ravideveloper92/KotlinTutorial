package com.demo.shaadi.datamodel


import com.google.gson.annotations.SerializedName


class Registered {

    @SerializedName("date")
    var date: String? = null

    @SerializedName("age")
    var age: String? = null
}