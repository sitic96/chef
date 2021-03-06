package db;

import entity.Category;
import entity.Ingredient;
import entity.Recipe;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import javax.xml.ws.WebServiceException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by sitora on 26.01.17.
 */
public class DBWorker {

    public static Ingredient readByIdIngredient(String objName, String table, Integer id) {
        try {
            QueryRunner run = new QueryRunner(Connector.getDataSource());
            ResultSetHandler<List<Ingredient>> h = new BeanListHandler<>((Class<Ingredient>) Class.forName("entity." + objName));
            List<Ingredient> query = run.query("SELECT * FROM " + table + " where id = " + id, h);
            return query.get(0);

        } catch (SQLException e) {
            throw new WebServiceException();
        } catch (ClassNotFoundException e) {
            throw new WebServiceException();
        }
    }

    public static Category readByIdСategory(String objName, String table, Integer id) {
        try {
            QueryRunner run = new QueryRunner(Connector.getDataSource());
            ResultSetHandler<List<Category>> h = new BeanListHandler<>((Class<Category>) Class.forName("entity." + objName));
            List<Category> query = run.query("SELECT * FROM " + table + " where id = " + id, h);
            return query.get(0);

        } catch (SQLException e) {
            throw new WebServiceException();
        } catch (ClassNotFoundException e) {
            throw new WebServiceException();
        }
    }

    public static Recipe readByIdRecipe(String objName, String table, Integer id) {
        try {
            QueryRunner run = new QueryRunner(Connector.getDataSource());
            ResultSetHandler<List<Recipe>> h = new BeanListHandler<>((Class<Recipe>) Class.forName("entity." + objName));
            List<Recipe> query = run.query("SELECT * FROM " + table + " where id = " + id, h);
            query.get(0).setIngredients(run.query("select * from Ingredients where id in (SELECT ingredient from Rec_Ing Rec_Ing WHERE recipe = " +
                    query.get(0).getId() + " )", new BeanListHandler<>(Ingredient.class)));
            return query.get(0);

        } catch (SQLException e) {
            throw new WebServiceException();
        } catch (ClassNotFoundException e) {
            throw new WebServiceException();
        }
    }

    public static List<Category> getAllCategories(String table, String objName) {
        try {
            QueryRunner run = new QueryRunner(Connector.getDataSource());
            ResultSetHandler<List<Category>> h = new BeanListHandler<>((Class<Category>) Class.forName("entity." + objName));
            List<Category> categories = run.query("SELECT * FROM " + table, h);
            return categories;

        } catch (SQLException e) {
            throw new WebServiceException();
        } catch (ClassNotFoundException e) {
            throw new WebServiceException();
        }
    }

    public static List<Recipe> gerRecipesByIngredients(List<String> ingredients) {
        StringBuilder sqlLine = new StringBuilder();
        sqlLine.append("SELECT r.name as name, r.id as id, r.category as category, r.text as text \n" +
                "FROM Recipes r\n" +
                "  JOIN Rec_Ing rp ON rp.recipe = r.id\n" +
                "  JOIN Ingredients ing ON ing.id = rp.ingredient\n" +
                "WHERE ing.id IN (SELECT id\n" +
                "                 FROM Ingredients\n" +
                "                 WHERE name IN ( ");
        for (int i = 0; i < ingredients.size(); i++) {
            sqlLine.append("\"" + ingredients.get(i));
            if (i + 1 < ingredients.size()) {
                sqlLine.append("\", ");
            } else {
                sqlLine.append("\"");
            }
        }
        sqlLine.append(" )) ");
        sqlLine.append("GROUP BY r.id ");
        sqlLine.append("HAVING count(ing.id) = " + ingredients.size() + ";");
        QueryRunner queryRunner = new QueryRunner(Connector.getDataSource());
        ResultSetHandler<List<Recipe>> resultSetHandler = new BeanListHandler<>(Recipe.class);
        try {
            List<Recipe> recipes = queryRunner.query(sqlLine.toString(), resultSetHandler);
            for (Recipe recipe : recipes
                    ) {
                recipe.setIngredients(queryRunner.query("select * from Ingredients where id in (SELECT ingredient from Rec_Ing Rec_Ing WHERE recipe = " +
                        recipe.getId() + " )", new BeanListHandler<>(Ingredient.class)));
            }
            return recipes;
        } catch (SQLException e) {
            throw new WebServiceException();
        }
    }

    public static List<Recipe> getAllRecipes(String table, String objName) {
        try {
            QueryRunner run = new QueryRunner(Connector.getDataSource());
            ResultSetHandler<List<Recipe>> h = new BeanListHandler<>((Class<Recipe>) Class.forName("entity." + objName));
            List<Recipe> recipes = run.query("SELECT * FROM " + table, h);
            for (Recipe recipe : recipes) {
                recipe.setIngredients(run.query("select * from Ingredients where id in (SELECT ingredient from Rec_Ing Rec_Ing WHERE recipe = " +
                        recipe.getId() + " )", new BeanListHandler<>(Ingredient.class)));
            }
            return recipes;

        } catch (SQLException e) {
            throw new WebServiceException();
        } catch (ClassNotFoundException e) {
            throw new WebServiceException();
        }
    }

    public static List<Recipe> getRandomRecipes(int count, String table, String objName) {
        try {
            QueryRunner run = new QueryRunner(Connector.getDataSource());
            ResultSetHandler<List<Recipe>> h = new BeanListHandler<>((Class<Recipe>) Class.forName("entity." + objName));
            List<Recipe> recipes = run.query("SELECT * FROM " + table + " ORDER BY RAND()\n" +
                    "LIMIT " + count, h);
            for (Recipe recipe : recipes) {
                recipe.setIngredients(run.query("select * from Ingredients where id in (SELECT ingredient from Rec_Ing Rec_Ing WHERE recipe = " +
                        recipe.getId() + " )", new BeanListHandler<>(Ingredient.class)));
            }
            return recipes;

        } catch (SQLException e) {
            throw new WebServiceException();
        } catch (ClassNotFoundException e) {
            throw new WebServiceException();
        }
    }

    public static List<Ingredient> getAllIngredients(String table, String objName) {
        try {
            QueryRunner run = new QueryRunner(Connector.getDataSource());
            ResultSetHandler<List<Ingredient>> h = new BeanListHandler<>((Class<Ingredient>) Class.forName("entity." + objName));
            List<Ingredient> categories = run.query("SELECT * FROM " + table, h);
            return categories;

        } catch (SQLException e) {
            throw new WebServiceException();
        } catch (ClassNotFoundException e) {
            throw new WebServiceException();
        }
    }

    public static void addNewCategory(String name) {
        if (!exist("Categories", name, "Name")) {
            insert("INSERT INTO `Categories`(`name`) VALUES (\"" + name + "\")");
        }

    }

    public static void addNewRecipe(String name, Integer category, String text) {
        if (exist("Categories", category, "Category")) {
            String sqlLine = "INSERT INTO `Recipes`(`name`, `category`, `text`) VALUES (\"" + name + "\", " + category + "\", " + text + "\")";
            insert(sqlLine);
        }
    }

    public static void addNewIngredient(String name) {
        if (!exist("Ingredients", name, "Name")) {
            insert("INSERT INTO `Ingredients`(`name`) VALUES (\"" + name + "\")");
        }
    }

    public static void removeCategory(Long id) {
        String sqlLine = "DELETE FROM `Categories` WHERE id = " + id;
        remove(sqlLine);
    }

    public static void removeRecipe(Long id) {
        String sqlLine = "DELETE FROM `Recipes` WHERE id = " + id;
        remove(sqlLine);
    }

    public static void removeIngredient(Long id) {
        String sqlLine = "DELETE FROM `Ingredients` WHERE id = " + id;
        remove(sqlLine);
    }

    public static void updateCategory(Long id, String newName) {
        String sqlLine = "UPDATE `Categories` SET 'NAME' = \"" + newName + "\" WHERE id = " + id;
        update(sqlLine);
    }

    public static void updateRecipe(Long id, String name, String text, Integer category) {
        String sqlLine = "UPDATE `Categories` SET 'NAME' = \"" + name + "\", 'text' = \"" + text + "\", 'category' = " + category + " WHERE id = " + id;
        update(sqlLine);
    }

    public static void updateIngredient(Long id, String name) {
        String sqlLine = "UPDATE `Ingredients` SET 'NAME' = \"" + name + "\" WHERE id = " + id;
        update(sqlLine);
    }

    private static boolean exist(String table, String parameter, String paramName) {
        String sqlLine = "SELECT * from " + table + " where `" + paramName + "` = " + "\"" + parameter + "\"";
        return exist(sqlLine);

    }

    private static boolean exist(String table, Integer param, String paramName) {
        String sqlLine = "SELECT * from " + table + " where `" + paramName + "` = " + param;
        return exist(sqlLine);

    }

    private static void insert(String sqlLine) {
        try {
            Connector.getDataSource().getConnection().createStatement().
                    executeUpdate(sqlLine);
        } catch (SQLException e) {
            throw new WebServiceException();
        }
    }

    private static void remove(String sqlLine) {
        try {
            Connection connector = Connector.connect();
            connector.createStatement().
                    executeUpdate(sqlLine);
            connector.close();
        } catch (SQLException e) {
            throw new WebServiceException();
        }
    }

    private static void update(String sqlLine) {
        try {
            Connection connector = Connector.connect();
            connector.createStatement().
                    executeUpdate(sqlLine);
            connector.close();
        } catch (SQLException e) {
            throw new WebServiceException();
        }
    }

    private static boolean exist(String sqlLine) {
        boolean exist = false;
        Connection conn = null;
        try {
            conn = Connector.connect();
            ResultSet rs = conn.createStatement().executeQuery(sqlLine);
            if (rs.next()) {
                exist = true;
            }
            rs.close();
            conn.close();
        } catch (Exception e) {
            throw new WebServiceException();
        }
        return exist;
    }
}
