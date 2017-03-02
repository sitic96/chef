package practice.chiefandroid.services;

import java.util.List;

import practice.chiefandroid.dao.Ingredient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by sitora on 23.02.17.
 */

public interface IngredientService {
    @GET("ingredient/all")
    Call<List<Ingredient>> ingredients();

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/rest/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
