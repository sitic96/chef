package practice.chiefandroid.dao;

import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by sitora on 07.02.17.
 */

public class Recipe {
    private List<Ingredient> ingredients;
    private Long id;
    private String text;
    private String name;

    public Recipe() {
    }

    public Recipe(List<Ingredient> ingredients, Long id, String text, String name) {
        this.ingredients = ingredients;
        this.id = id;
        this.text = text;
        this.name = name;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "ingredients=" + ingredients +
                ", id=" + id +
                ", text='" + text + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
