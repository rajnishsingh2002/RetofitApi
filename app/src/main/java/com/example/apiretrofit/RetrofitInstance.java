package com.example.apiretrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
//    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";
//    private static final String BASE_URL = "https://crudcrud.com/api/46cdca54290a424dbd1958ce6ef1eb5a/";
//    private static final String BASE_URL = "https://crudcrud.com/api/1583650b07254d8ab84ca2623aa9d8ba/";
    private static final String BASE_URL = "https://retrofit.free.beeceptor.com/api/";
    private static Retrofit retrofit;

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static ApiService getApi(){
        return getRetrofitInstance().create(ApiService.class);
    }
}
