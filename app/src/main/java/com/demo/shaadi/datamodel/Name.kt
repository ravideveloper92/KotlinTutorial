package com.demo.shaadi.datamodel


import com.google.gson.annotations.SerializedName


class Name {

    @SerializedName("last")
    var last: String? = null

    @SerializedName("title")
    var title: String? = null

    @SerializedName("first")
    var first: String? = null
}