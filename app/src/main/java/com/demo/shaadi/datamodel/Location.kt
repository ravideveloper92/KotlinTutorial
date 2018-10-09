package com.demo.shaadi.datamodel


import com.google.gson.annotations.SerializedName


class Location {

    @SerializedName("city")
    var city: String? = null

    @SerializedName("street")
    var street: String? = null

    @SerializedName("timezone")
    var timezone: Timezone? = null

    @SerializedName("postcode")
    var postcode: String? = null

    @SerializedName("coordinates")
    var coordinates: Coordinates? = null

    @SerializedName("state")
    var state: String? = null
}