package com.demo.shaadi.datamodel


import com.google.gson.annotations.SerializedName


class ResultsItem {

    @SerializedName("nat")
    var nat: String? = null

    @SerializedName("gender")
    var gender: String? = null

    @SerializedName("phone")
    var phone: String? = null

    @SerializedName("dob")
    var dob: Dob? = null

    @SerializedName("name")
    var name:Name? = null

    @SerializedName("registered")
    var registered: Registered? = null

    @SerializedName("location")
    var location: Location? = null

    @SerializedName("id")
    var id: Id? = null

    @SerializedName("login")
    var login: Login? = null

    @SerializedName("cell")
    var cell: String? = null

    @SerializedName("email")
    var email: String? = null

    @SerializedName("picture")
    var picture: Picture? = null
}