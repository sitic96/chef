package data;

import javax.persistence.*;
import java.math.BigInteger;

/**
 * Created by sitora on 20.07.17.
 */
@Entity
@Table(name = "users_likes", schema = "public", catalog = "Chief")
@IdClass(UsersLikesPK.class)
public class UsersLikes {
    private BigInteger id;
    private BigInteger userId;
    private BigInteger recipeId;

    @Basic
    @Column(name = "id", nullable = false, precision = 0)
    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    @Id
    @Column(name = "user_id", nullable = false, precision = 0)
    public BigInteger getUserId() {
        return userId;
    }

    public void setUserId(BigInteger userId) {
        this.userId = userId;
    }

    @Id
    @Column(name = "recipe_id", nullable = false, precision = 0)
    public BigInteger getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(BigInteger recipeId) {
        this.recipeId = recipeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsersLikes that = (UsersLikes) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (recipeId != null ? !recipeId.equals(that.recipeId) : that.recipeId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (recipeId != null ? recipeId.hashCode() : 0);
        return result;
    }
}
