package com.demo.shaadi.datamodel

import com.google.gson.annotations.SerializedName


data class ShaadiUserResponse(

        @SerializedName("results")
        var results: List<ResultsItem>? = null

        ,

        @SerializedName("info")
        var info: Info? = null

)
