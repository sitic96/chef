package data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

/**
 * Created by sitora on 21.07.17.
 */
@XmlRootElement()
public class CompleteRecipe {
    @XmlElement(name="recipe_id")
    public BigDecimal recipe_id;
    @XmlElement(name="recipe_name")
    public String recipe_name;
//    @XmlElement(name="img_link")
//    public String img_link;
//    @XmlElement(name="user_id")
//    public BigInteger user_id;
    @XmlElement(name="user_name")
    public String user_name;
//    @XmlElement(name="category")
//    public String category;
//    @XmlElement(name="profile_picture")
//    public String profile_picture;
    @XmlElement(name="likes")
    public Integer likes;
    @XmlElement(name="liked")
    public String liked;

//    public CompleteRecipe(BigInteger recipe_id, String recipe_name, String img_link, BigInteger user_id, String user_name, String profile_picture, String category) {
//        this.recipe_id = recipe_id;
//        this.recipe_name = recipe_name;
//        this.img_link = img_link;
//        this.user_id = user_id;
//        this.user_name = user_name;
//        this.category = category;
//        this.profile_picture = profile_picture;
//    }

    public CompleteRecipe(BigDecimal recipe_id, String recipe_name, String user_name, Integer likes, String liked) {
        this.recipe_id = recipe_id;
        this.recipe_name = recipe_name;
//        this.img_link = img_link;
//        this.user_id = user_id;
        this.user_name = user_name;
//        this.category = category;
//        this.profile_picture = profile_picture;
        this.likes = likes;
        this.liked = liked;
    }

    public CompleteRecipe() {
    }

    public BigDecimal getRecipe_id() {
        return recipe_id;
    }

    public void setRecipe_id(BigDecimal recipe_id) {
        this.recipe_id = recipe_id;
    }

    public String getRecipe_name() {
        return recipe_name;
    }

    public void setRecipe_name(String recipe_name) {
        this.recipe_name = recipe_name;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public Object getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public String getLiked() {
        return liked;
    }

    public void setLiked(String liked) {
        this.liked = liked;
    }
}
