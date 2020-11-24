package com.example.hackathon.API


import com.example.hackathon.DTO.Data
import com.example.hackathon.DTO.SignUp
import com.example.hackathon.DTO.UserLogin
import com.example.hackathon.DTO.User
import retrofit2.Call
import retrofit2.http.*

interface UserAPI {
    @FormUrlEncoded
    @POST("/3000/v1/signUp/basic")
    fun signUp(
        @Field("name") name: String,
        @Field("id") id: String,
        @Field("password") password: String,
        @Field("userInfo") userInfo: String
    ) : Call<SignUp>
    @FormUrlEncoded
    @POST("/3000/v1/signIn/basic")
    fun signIn(
        @Field("id") id : String,
        @Field("password") password: String
    ) : Call<UserLogin>
    @GET("/3001/v1/mypage/basic/{id}")
    fun getUser(
        @Path("id") id : String
    ) : Call<Data>
}