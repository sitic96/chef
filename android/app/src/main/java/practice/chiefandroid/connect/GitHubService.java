package practice.chiefandroid.connect;

import java.util.List;

import practice.chiefandroid.dao.Recipe;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by sitora on 06.02.17.
 */

public interface GitHubService {
    @GET("recipes/all")
    Call<List<Recipe>> repoContributors();
//http://localhost:8080/rest/categories/all
    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/rest/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}