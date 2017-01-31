package wrappers;

import entity.Entity;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * Created by sitora on 29.01.17.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "category"
})
@XmlRootElement(name = "categories")
public class Wrapper {
    @XmlElement(required = true)
    List<Entity> category;

    public Wrapper() {
    }

    public Wrapper(List<Entity> entities) {
        this.category = entities;
    }

    //@XmlElement
    public List<Entity> getCategory() {
        return category;
    }

}