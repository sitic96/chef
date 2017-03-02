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
            .baseUrl("http://10.0.2.2:8080/rest/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    //    public interface GitHubService {
//        @GET("recipes/all")
//        Call<List<RecipeActivity>> repoContributors();
//        //http://localhost:8080/rest/categories/all
//        public static final Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://10.0.2.2:8080/rest/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//    }

    @POST("recipes/getbyingredients")
    Call<List<Recipe>> recipes(@Body HashSet<String> ingredients);
}
