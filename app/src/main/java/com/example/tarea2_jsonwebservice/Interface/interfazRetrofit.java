package com.example.tarea2_jsonwebservice.Interface;
import com.example.tarea2_jsonwebservice.Model.Posts;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface interfazRetrofit {
    @Headers({"Public-Merchant-Id:12d7a9b02e474a708635c795e6bc8909"})

    @GET("transfer/v1/bankList")
    Call<List<Posts>> getPost();

}
