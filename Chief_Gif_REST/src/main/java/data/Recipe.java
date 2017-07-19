package data;

import javax.persistence.*;
import java.math.BigInteger;

/**
 * Created by sitora on 15.07.17.
 */
@Entity
public class Recipe {
    private BigInteger id;
    private String name;
    private String imgLink;
    private Category categoryByCategory;
    private BigInteger author;

    @Id
    @Column(name = "id", nullable = false, precision = 0)
    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = -1)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

        if (id != null ? !id.equals(recipe.id) : recipe.id != null) return false;
        if (name != null ? !name.equals(recipe.name) : recipe.name != null) return false;
        if (imgLink != null ? !imgLink.equals(recipe.imgLink) : recipe.imgLink != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
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
