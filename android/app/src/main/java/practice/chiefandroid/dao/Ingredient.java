package practice.chiefandroid.dao;

import java.util.PriorityQueue;

/**
 * Created by sitora on 07.02.17.
 */

public class Ingredient {

    private Long id;
    private String name;

    public Ingredient(){}

    public Ingredient(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
