package data;

import javax.persistence.*;
import java.math.BigInteger;

/**
 * Created by sitora on 15.07.17.
 */
@Entity
@Table(name = "`Recipe`")
public class Recipe {
    private BigInteger recipe_id;
    private String recipe_name;
    private String imgLink;
    private Category categoryByCategory;
    private BigInteger author;

    public Recipe(BigInteger recipe_id, String name, String imgLink, Category categoryByCategory, BigInteger author) {
        this.recipe_id = recipe_id;
        this.recipe_name = name;
        this.imgLink = imgLink;
        this.categoryByCategory = categoryByCategory;
        this.author = author;
    }

    public Recipe() {
    }

    @Id
    @Column(name = "recipe_id", nullable = false, precision = 0)
    public BigInteger getRecipe_id() {
        return recipe_id;
    }

    public void setRecipe_id(BigInteger id) {
        this.recipe_id = id;
    }

    @Basic
    @Column(name = "recipe_name", nullable = false, length = -1)
    public String getRecipe_name() {
        return recipe_name;
    }

    public void setRecipe_name(String name) {
        this.recipe_name = name;
    }

    @Basic
    @Column(name = "img_link", nullable = false, length = -1)
    public String getImgLink() {
        return imgLink;
    }

    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Recipe recipe = (Recipe) o;

        if (recipe_id != null ? !recipe_id.equals(recipe.recipe_id) : recipe.recipe_id != null) return false;
        if (recipe_name != null ? !recipe_name.equals(recipe.recipe_name) : recipe.recipe_name != null) return false;
        if (imgLink != null ? !imgLink.equals(recipe.imgLink) : recipe.imgLink != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = recipe_id != null ? recipe_id.hashCode() : 0;
        result = 31 * result + (recipe_name != null ? recipe_name.hashCode() : 0);
        result = 31 * result + (imgLink != null ? imgLink.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "Category", referencedColumnName = "id", nullable = false)
    public Category getCategoryByCategory() {
        return categoryByCategory;
    }

    public void setCategoryByCategory(Category categoryByCategory) {
        this.categoryByCategory = categoryByCategory;
    }

    @Basic
    @Column(name = "author", nullable = false, precision = 0)
    public BigInteger getAuthor() {
        return author;
    }

    public void setAuthor(BigInteger author) {
        this.author = author;
    }
}
