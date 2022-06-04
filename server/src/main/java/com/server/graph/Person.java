package com.server.graph;

import java.util.ArrayList;
import java.util.List;

public abstract class Person <T>{
    char name;
    List<T> orderedPreferences = new ArrayList<>();
    T partner;

    public List<T> getOrderedPreferences() {
        return orderedPreferences;
    }

    public T getPartner() {
        return partner;
    }

    public char getName() {
        return name;
    }

    public void setPartner(T partner) {
        this.partner = partner;
    }

    public void setName(char name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "Person{" +
                "name=" + name +
                ", partner=" + partner +
                '}';
    }
}
