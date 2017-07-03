package practice.chiefandroid.services;

import java.util.HashSet;
import java.util.List;

import practice.chiefandroid.dao.Recipe;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by sitora on 08.02.17.
 */

public interface ChiefService {
    @GET("recipes/55")
    Call<Recipe> recipe();

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://192.168.56.1:8181/rest/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    @POST("recipes/getbyingredients")
    Call<List<Recipe>> recipes(@Body HashSet<String> ingredients);
}
