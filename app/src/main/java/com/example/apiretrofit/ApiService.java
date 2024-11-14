package com.example.apiretrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @GET("users")
    Call<List<User>> getUsers();

    @POST("users")
    Call<User> createUser(@Body User user);

    @PUT("users/{id}")
    Call<User> updateUser(@Path("id")String id,@Body User user);

    @DELETE("users/{id}")
    Call<Void> deleteUser(@Path("id") String id);

    @GET("users")
    Call<List<User>> getSortedUser(@Query("sort") String sortBy);

    @FormUrlEncoded
    @POST("user")
    Call<User> emailData(@Field("name") String name,@Field("email")String email);
}


















/*
url=https://example.com/api/

@GET("users")
Call<List<User>> getUsers(@Query("sort") String sortBy);  getUser("name")

 https://example.com/api/users?sort=name






 */