package practice.chiefandroid;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import practice.chiefandroid.connect.ChiefService;
import practice.chiefandroid.connect.IngredientService;
import practice.chiefandroid.dao.Ingredient;
import practice.chiefandroid.dao.Recipe;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {
    private List<Ingredient> ingredients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        getAllCategories();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
//        ArrayList<Integer> ages = new ArrayList<>();
//        ages.add(20);
//        ages.add(30);
//
//        ISearchProfilePost iSearchProfile = gsonServerAPIRetrofit.create(ISearchProfilePost.class);
//        Call<ResponseBody> call = iSearchProfile.postSearchProfile(
//                ages
//        );

        ArrayList<String> ingredients = new ArrayList<>();
        ingredients.add("Соль");
        ingredients.add("Вода");
        getRecipesByIngredients(ingredients);

    }

    protected void getRecipesByIngredients(ArrayList<String> ingredients) {

        ChiefService chiefService = ChiefService.retrofit.create(ChiefService.class);
        final Call<List<Recipe>> call =
                chiefService.recipes(ingredients);
        call.enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                List<Recipe> recipe = response.body();
            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
                int h = 8;
                h++;
            }
        });
    }


    protected void getAllCategories() {
        final IngredientService ingredientService = IngredientService.retrofit.create(IngredientService.class);
        final Call<List<Ingredient>> call =
                ingredientService.ingredients();
        call.enqueue(new Callback<List<Ingredient>>() {
            @Override
            public void onResponse(Call<List<Ingredient>> call, Response<List<Ingredient>> response) {
                ingredients = response.body();
                fillSpinner((Spinner) findViewById(R.id.ingredients), ingredients);
            }

            @Override
            public void onFailure(Call<List<Ingredient>> call, Throwable t) {

            }
        });
    }

    protected void fillSpinner(Spinner dropdown, List<Ingredient> ingredients) {
        Ingredient[] items = ingredients.toArray(new Ingredient[ingredients.size()]);
        ArrayAdapter<Ingredient> adapter = new ArrayAdapter<Ingredient>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
    }

    private static int newSpinnerPosition = 2;

    public void addSpinner(View view) {
        LinearLayout layout = (LinearLayout) findViewById(R.id.content_search);
        Spinner spinner = new Spinner(this);
        fillSpinner(spinner, this.ingredients);
        layout.addView(spinner, newSpinnerPosition);
        newSpinnerPosition++;
    }

    public void findRecipe(View view) {

    }
}
