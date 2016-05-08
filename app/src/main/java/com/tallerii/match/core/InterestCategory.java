package com.tallerii.match.core;

import java.io.Serializable;
import java.util.Vector;

/**
 * Created by Demian on 24/04/2016.
 */
public class InterestCategory implements Serializable {
    private String name;
    private Vector<String> details;

    InterestCategory(String name){
        this.name = name;
        details = new Vector<>();
    }

    public Vector<String> getDetails() {
        return details;
    }

    public String getName() {
        return name;
    }

    public void addDetail(String detail) {
        this.details.add(detail);
    }


}
