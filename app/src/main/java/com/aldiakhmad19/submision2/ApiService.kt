package com.aldiakhmad19.submision2

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("search/users")
    @Headers("Authorization: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")
    fun getSearchUsers(
        @Query("q") query: String
    ): Call<SearchUser>

    @GET("users/{username}")
    @Headers("Authorization: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")
    fun getUserDetail(
        @Path("username") username: String
    ): Call<DetailUser>

    @GET("users/{username}/followers")
    @Headers("Authorization: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")
    fun getFollowers(
        @Path("username") username: String
    ): Call<ArrayList<ItemsItem>>

    @GET("users/{username}/following")
    @Headers("Authorization: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")
    fun getFollowing(
        @Path("username") username: String
    ): Call<ArrayList<ItemsItem>>
}