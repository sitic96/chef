package practice.chiefandroid.data;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import practice.chiefandroid.SearchActivity;
import practice.chiefandroid.dao.Recipe;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by sitora on 01.03.17.
 */

public class SaveRecipes {
    private static final String KEY_CONNECTIONS = "RECIPES";
    private static final Context applicationContext = SearchActivity.getContextOfApplication();
    private static final SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(applicationContext);

    public static void saveRecipe(Recipe recipe) {
        List<Recipe> recipes = getRecipes();
        if (recipes != null) {
            recipes.add(recipe);
        } else {
            recipes = new ArrayList<>();
            recipes.add(recipe);
        }
        saveRecipes(recipes);
    }

    protected static void saveRecipes(List<Recipe> recipes) {
        SharedPreferences.Editor editor = mPrefs.edit();
        //SharedPreferences.Editor editor = activity.getPreferences(MODE_PRIVATE).edit();
        String connectionsJSONString = new Gson().toJson(recipes);
        editor.putString(KEY_CONNECTIONS, connectionsJSONString);
        editor.commit();
    }

    public static List<Recipe> getRecipes() {
        String connectionsJSONString = mPrefs.getString(KEY_CONNECTIONS, null);
        Type type = new TypeToken<List<Recipe>>() {
        }.getType();
        List<Recipe> recipes = new Gson().fromJson(connectionsJSONString, type);
        return recipes;
    }
}
