package practice.chiefandroid;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.StyleRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import practice.chiefandroid.connect.ChiefService;
import practice.chiefandroid.dao.Ingredient;
import practice.chiefandroid.dao.Recipe;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Main2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    protected DrawerLayout mDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        final Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                ChiefService chiefService = ChiefService.retrofit.create(ChiefService.class);
                final Call<Recipe> call =
                        chiefService.recipe();

                call.enqueue(new Callback<Recipe>() {
                    @Override
                    public void onResponse(Call<Recipe> call, Response<Recipe> response) {
                        Recipe recipe = response.body();
                        fillElements(recipe);
                        button.setVisibility(View.INVISIBLE);
                        ImageButton imageButton = (ImageButton) findViewById(R.id.favorite_button);
                        ViewSwitcher viewSwitcher = (ViewSwitcher) findViewById(R.id.button_switcher);
                        viewSwitcher.setVisibility(View.VISIBLE);
                        imageButton.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onFailure(Call<Recipe> call, Throwable t) {
                        // final TextView textView = (TextView) findViewById(R.id.textView);
                        // textView.setText("Something went wrong: " + t.getMessage());
                    }
                });
            }
        });
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
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void ShowList(View view) {
        ListView listView = (ListView) findViewById(R.id.lvMain);
        ViewSwitcher viewSwitcher = (ViewSwitcher) findViewById(R.id.button_switcher);
        listView.setVisibility(View.VISIBLE);
        viewSwitcher.showNext();
    }


    public void HideList(View view) {
        ListView listView = (ListView) findViewById(R.id.lvMain);
        ViewSwitcher viewSwitcher = (ViewSwitcher) findViewById(R.id.button_switcher);
        viewSwitcher.showNext();
        listView.setVisibility(View.GONE);
    }
}
