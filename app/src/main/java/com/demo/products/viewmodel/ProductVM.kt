package com.demo.products.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import com.demo.products.datamodel.*
import com.demo.products.retrofit.ApiService
import com.demo.products.retrofit.RetrofitClient
import com.google.gson.Gson
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductVM (app: Application): AndroidViewModel(app), LifecycleObserver, Callback<ResponseBody> {
    val teamsList = MutableLiveData<HashMap<String,ArrayList<ProductInfoResponseItem>>>()
    var teamshmap = HashMap<String, ArrayList<ProductInfoResponseItem>>()

    init {
        callProductDetailsAPI()
    }

    public fun callProductDetailsAPI() {
        var call: Call<ResponseBody>? = null
        call = RetrofitClient.getRetrofit().create(ApiService::class.java).getProductDetails();
        call.enqueue(this)

    }

    override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
        teamsList.value=teamshmap
    }

    override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {
        var json =response?.body()!!.string()
        val productInfoResponse = Gson().fromJson(json, ProductInfoResponse::class.java)
        val smartproductInfoResponseItemList=ArrayList<ProductInfoResponseItem>()
        val frequentproductInfoResponseItemList=ArrayList<ProductInfoResponseItem>()
        for (product in productInfoResponse) {
            if (product.smartRecommendation) {
                smartproductInfoResponseItemList.add(product)
            } else {
                frequentproductInfoResponseItemList.add(product)
            }
        }
        teamshmap.put("Recommended", smartproductInfoResponseItemList)
        teamshmap.put("Frequently Ordered", frequentproductInfoResponseItemList)
        teamsList.value=teamshmap

    }

}
