package com.demo.shaadi.datamodel


import com.google.gson.annotations.SerializedName


class Info {

    @SerializedName("seed")
    var seed: String? = null

    @SerializedName("page")
    var page: String? = null

    @SerializedName("results")
    var results: String? = null

    @SerializedName("version")
    var version: String? = null
}