package com.aldiakhmad19.submision2

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowingViewModel : ViewModel() {
    val following = MutableLiveData<ArrayList<ItemsItem>>()

    fun setListFollowers(username: String){
        val client = ApiConfig.getApiService()
            .getFollowing(username)
            .enqueue(object : Callback<ArrayList<ItemsItem>> {
                override fun onResponse(
                    call: Call<ArrayList<ItemsItem>>,
                    response: Response<ArrayList<ItemsItem>>
                ) {
                    if (response.isSuccessful){
                        following.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<ArrayList<ItemsItem>>, t: Throwable) {
                    Log.d("Failure", t.message.toString())
                }

            })
    }

    fun getListFollowing(): LiveData<ArrayList<ItemsItem>> {
        return following
    }
}