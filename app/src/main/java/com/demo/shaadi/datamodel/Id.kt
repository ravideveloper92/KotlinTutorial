package com.demo.shaadi.datamodel


import com.google.gson.annotations.SerializedName


class Id {

    @SerializedName("name")
    var name: String? = null

    @SerializedName("value")
    var value: Any? = null
}