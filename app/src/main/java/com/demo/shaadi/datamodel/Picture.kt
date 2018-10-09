package com.demo.shaadi.datamodel


import com.google.gson.annotations.SerializedName


class Picture {

    @SerializedName("thumbnail")
    var thumbnail: String? = null

    @SerializedName("large")
    var large: String? = null

    @SerializedName("medium")
    var medium: String? = null
}