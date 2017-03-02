package practice.chiefandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import java.util.List;

import practice.chiefandroid.dao.Recipe;
import practice.chiefandroid.data.SaveRecipes;

public class FavListActivity extends AppCompatActivity {
private List<Recipe> recipes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav_list);

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

        recipes = SaveRecipes.getRecipes(this);
        fillRecipes(recipes);
        ListView lv = (ListView) findViewById(R.id.resultList);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getBaseContext(), RecipeActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("recipe", recipes.get((int) id));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
    protected void fillRecipes(List<practice.chiefandroid.dao.Recipe> recipes) {
        ListView resultList = (ListView) findViewById(R.id.resultList);
        ArrayAdapter<Recipe> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                recipes.toArray(new practice.chiefandroid.dao.Recipe[recipes.size()]));
        resultList.setAdapter(adapter);
    }
}
