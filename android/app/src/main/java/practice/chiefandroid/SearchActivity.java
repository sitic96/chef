package practice.chiefandroid;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.List;

import practice.chiefandroid.connect.IngredientService;
import practice.chiefandroid.dao.Ingredient;
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
    }

    protected void getAllCategories() {
        final IngredientService ingredientService = IngredientService.retrofit.create(IngredientService.class);
        final Call<List<Ingredient>> call =
                ingredientService.ingredients();
        call.enqueue(new Callback<List<Ingredient>>() {
            @Override
            public void onResponse(Call<List<Ingredient>> call, Response<List<Ingredient>> response) {
                ingredients = response.body();
                fillSpinner(ingredients);
            }

            @Override
            public void onFailure(Call<List<Ingredient>> call, Throwable t) {

            }
        });
    }

    protected void fillSpinner(List<Ingredient> ingredients) {
        Spinner dropdown = (Spinner) findViewById(R.id.ingredients);
        Ingredient[] items = ingredients.toArray(new Ingredient[ingredients.size()]);
        ArrayAdapter<Ingredient> adapter = new ArrayAdapter<Ingredient>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
    }

}
