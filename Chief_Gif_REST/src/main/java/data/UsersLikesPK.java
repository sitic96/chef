package data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigInteger;

/**
 * Created by sitora on 20.07.17.
 */
public class UsersLikesPK implements Serializable {
    private BigInteger userId;
    private BigInteger recipeId;

    @Column(name = "user_id", nullable = false, precision = 0)
    @Id
    public BigInteger getUserId() {
        return userId;
    }

    public void setUserId(BigInteger userId) {
        this.userId = userId;
    }

    @Column(name = "recipe_id", nullable = false, precision = 0)
    @Id
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

        UsersLikesPK that = (UsersLikesPK) o;

        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (recipeId != null ? !recipeId.equals(that.recipeId) : that.recipeId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (recipeId != null ? recipeId.hashCode() : 0);
        return result;
    }
}
