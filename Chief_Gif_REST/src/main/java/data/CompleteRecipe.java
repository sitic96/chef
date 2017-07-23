package data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigInteger;

/**
 * Created by sitora on 21.07.17.
 */
@XmlRootElement()
public class CompleteRecipe {
    @XmlElement(name="recipe_id")
    public BigInteger recipe_id;
    @XmlElement(name="recipe_name")
    public String recipe_name;
    @XmlElement(name="img_link")
    public String img_link;
    @XmlElement(name="user_id")
    public BigInteger user_id;
    @XmlElement(name="user_name")
    public String user_name;
    @XmlElement(name="category")
    public String category;
    @XmlElement(name="profile_picture")
    public String profile_picture;

    public CompleteRecipe(BigInteger recipe_id, String recipe_name, String img_link, BigInteger user_id, String user_name, String profile_picture, String category) {
        this.recipe_id = recipe_id;
        this.recipe_name = recipe_name;
        this.img_link = img_link;
        this.user_id = user_id;
        this.user_name = user_name;
        this.category = category;
        this.profile_picture = profile_picture;
    }

    public CompleteRecipe() {
    }
}
