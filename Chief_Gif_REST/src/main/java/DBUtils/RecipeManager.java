package DBUtils;

import data.CompleteRecipe;
import org.hibernate.Session;
import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;

/**
 * Created by sitora on 22.07.17.
 */
public class RecipeManager {
    public CompleteRecipe getRecipeById(@NotNull BigInteger id) {
        Session session = Connector.getConnector().getSession();
        try {
            CompleteRecipe recipe = (CompleteRecipe) session.createQuery("SELECT NEW data.CompleteRecipe(recipe.recipe_id, recipe.recipe_name, recipe.imgLink, user.user_id, user.user_name, user.profilePicture, category.name) from Recipe recipe, User user, Category category where recipe.recipe_id=1").uniqueResult();

            return recipe;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }
}
