package com.aldiakhmad19.submision2

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel : ViewModel() {
    val listUsers = MutableLiveData<ArrayList<ItemsItem>>()
    fun setSearchUsers(query: String){
        val client = ApiConfig.getApiService().getSearchUsers(query)
        client.enqueue(object: Callback<SearchUser> {
            override fun onResponse(
                call: Call<SearchUser>,
                response: Response<SearchUser>
            ) {
                if (response.isSuccessful){
                    listUsers.postValue(response.body()?.items as ArrayList<ItemsItem>?)
                }
            }

            override fun onFailure(call: Call<SearchUser>, t: Throwable) {
                Log.e("Failure", t.message.toString())
            }
        })
    }

    fun getSearchUsers(): LiveData<ArrayList<ItemsItem>> {
        return listUsers
    }

}
