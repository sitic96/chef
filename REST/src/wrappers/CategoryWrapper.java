package wrappers;

import entity.Category;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sitora on 29.01.17.
 */

@XmlRootElement
public class CategoryWrapper {
    List<Category> categories = new ArrayList<>();

    public CategoryWrapper() {
    }

    public CategoryWrapper(List<Category> categories) {
        this.categories = categories;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

}