package practice.chiefandroid.data;

import android.app.Activity;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import practice.chiefandroid.dao.Recipe;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by sitora on 01.03.17.
 */

public class SaveRecipes {
    private static final String KEY_CONNECTIONS = "RECIPES";
    protected SharedPreferences mPrefs;


    public static void saveRecipe(Activity a, Recipe recipe) {
        List<Recipe> recipes = getRecipes(a);
        if (recipes!=null) {
            recipes.add(recipe);
        }
        else {
            recipes = new ArrayList<>();
            recipes.add(recipe);
        }
        saveRecipes(recipes, a);
    }

    protected static void saveRecipes(List<Recipe> recipes, Activity a) {
        SharedPreferences.Editor editor = a.getPreferences(MODE_PRIVATE).edit();
        String connectionsJSONString = new Gson().toJson(recipes);
        editor.putString(KEY_CONNECTIONS, connectionsJSONString);
        editor.commit();
    }

    public static List<Recipe> getRecipes(Activity a) {
        String connectionsJSONString = a.getPreferences(MODE_PRIVATE).getString(KEY_CONNECTIONS, null);
        Type type = new TypeToken<List<Recipe>>() {
        }.getType();
        List<Recipe> recipes = new Gson().fromJson(connectionsJSONString, type);
        return recipes;
    }
}
