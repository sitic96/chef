package practice.chiefandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import practice.chiefandroid.connect.ChiefService;
import practice.chiefandroid.dao.Ingredient;
import practice.chiefandroid.dao.Recipe;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeActivity extends AppCompatActivity {
protected Recipe recipe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recipe =  getIntent().getParcelableExtra("recipe");
        fillElements(recipe);
//        final Button button = (Button) findViewById(R.id.button);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ChiefService chiefService = ChiefService.retrofit.create(ChiefService.class);
//                final Call<Recipe> call =
//                        chiefService.recipe();
//
//                call.enqueue(new Callback<Recipe>() {
//                    @Override
//                    public void onResponse(Call<Recipe> call, Response<Recipe> response) {
//                        Recipe recipe = response.body();
//                        fillElements(recipe);
//                        button.setVisibility(View.INVISIBLE);
//                        //getArray(list);
//                    }
//
//                    @Override
//                    public void onFailure(Call<Recipe> call, Throwable t) {
//                        // final TextView textView = (TextView) findViewById(R.id.textView);
//                        // textView.setText("Something went wrong: " + t.getMessage());
//                    }
//                });
//            }
//        });
    }

    protected void fillElements(Recipe recipe) {
        final TextView textView = (TextView) findViewById(R.id.recipe);
        final TextView textView1 = (TextView) findViewById(R.id.recipeText);
        ListView lvMain = (ListView) findViewById(R.id.lvMain);
        ArrayAdapter<Ingredient> adapter = new ArrayAdapter<Ingredient>(this,
                android.R.layout.simple_list_item_1, recipe.getIngredients().toArray(new Ingredient[recipe.getIngredients().size()]));
        lvMain.setAdapter(adapter);
        textView.setText(recipe.getName());
        textView1.setText(recipe.getText());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
