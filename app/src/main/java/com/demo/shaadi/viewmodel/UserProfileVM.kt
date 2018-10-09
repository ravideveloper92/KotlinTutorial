package com.demo.shaadi.viewmodel

import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.demo.shaadi.datamodel.ShaadiUserResponse
import com.demo.shaadi.datamodel.ResultsItem
import com.demo.shaadi.retrofit.ApiService
import com.demo.shaadi.retrofit.RetrofitClient
import com.google.gson.Gson
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserProfileVM: ViewModel(), LifecycleObserver, Callback<ResponseBody> {

    // val userList: MutableLiveData<List<ResultsItem>>? = null
    val userList = MutableLiveData<List<ResultsItem>>()


    init {

        getUsers()
    }

    public fun getUsers() {
        var call: Call<ResponseBody>? = null
        call = RetrofitClient.getRetrofit().create(ApiService::class.java).getShaadiUsers("10");
        call.enqueue(this)

    }

    override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
        userList.value=null
    }

    override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {
        println("json is "+ call?.request()!!.url())
        var json=response?.body()!!.string()
        println("json body is "+ json)
        val shaadiUserResponse = Gson().fromJson(json, ShaadiUserResponse::class.java)
        println(shaadiUserResponse)
        println(shaadiUserResponse.results?.size)

      //  userList= MutableLiveData<List<ResultsItem>>()
        userList.value=shaadiUserResponse.results

    }

}
