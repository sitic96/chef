package practice.chiefandroid.dao;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by sitora on 07.02.17.
 */

public class Recipe implements Parcelable {
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

    public Recipe(Parcel in) {
        id = in.readLong();
        ingredients = new ArrayList<>();
        in.readList(ingredients, null);
        text = in.readString();
        name = in.readString();
    }

    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };

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
        return name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeList(ingredients);
        dest.writeString(text);
        dest.writeString(name);
    }
}
