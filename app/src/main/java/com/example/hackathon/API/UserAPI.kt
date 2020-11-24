package com.example.hackathon.API


import com.example.hackathon.DTO.SignUp
import com.example.hackathon.DTO.User
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface UserAPI {
    @FormUrlEncoded
    @POST("/v1/signUp/basic")
    fun signUp(
        @Field("name") name: String,
        @Field("id") id: String,
        @Field("password") password: String,
        @Field("userInfo") userInfo: String
    ) : Call<SignUp>
    @FormUrlEncoded
    @POST("/v1/signIn/basic")
    fun signIn(
        @Field("id") id : String,
        @Field("password") password: String
    ) : Call<User>
}