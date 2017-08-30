package DBUtils;

import data.CompleteRecipe;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by sitora on 22.07.17.
 */
public class RecipeManager {
    public CompleteRecipe getRecipeById(@NotNull BigInteger id) {
        Session session = Connector.getConnector().getSession();
        try {
            String query = "SELECT NEW data.CompleteRecipe(rec.recipe_id, rec.recipe_name, rec.imgLink, user.user_id, user.user_name, " +
                    "user.profilePicture, category.name) from Recipe rec, User user, Category category where rec.recipe_id = " + id;
            CompleteRecipe recipe = (CompleteRecipe) session.createQuery(query).getSingleResult();

            return recipe;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    @NotNull
    public CompleteRecipe getCompleteRecipeById(@NotNull BigInteger id) {
        Session session = Connector.getConnector().getSession();
        try {
            String sql = "SELECT recipe_id, recipe_name, img_link, user_name, profile_picture, likes, liked from getCompleteRECIPE(" + id + ");";
            List<CompleteRecipe> recipes = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(CompleteRecipe.class)).list();

            return recipes.get(0);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }
}
