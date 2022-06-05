package com.server.graph;

import java.util.ArrayList;
import java.util.List;

public abstract class Person <T>{
    String name;
    List<T> orderedPreferences = new ArrayList<>();
    String partnerName;

    public List<T> getOrderedPreferences() {
        return orderedPreferences;
    }

    public String getPartner() {
        return partnerName;
    }

    public String getName() {
        return name;
    }

    public void setPartner(String partner) {
        this.partnerName = partner;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPreferencesAsString() {
        StringBuilder result = new StringBuilder();
        result.append("[ ");

        for(T person : getOrderedPreferences()) {
            result.append(((Person)person).getName() + ' ');
        }
        result.append(']');
        return result.toString();
    }


    @Override
    public String toString() {
        return "Person{" +
                "name=" + name +
                ", partner=" + partnerName +
                '}';
    }

    public String getJson(){
        StringBuilder info = new StringBuilder();
        info.append("(name='").append(name).append("';partnerName='")
                .append(partnerName).append("';preferences='").append(getPreferencesAsJson())
                .append("')");
        return info.toString();
    }

    private String getPreferencesAsJson() {
        StringBuilder info = new StringBuilder();
        for (T person : orderedPreferences) {
            info.append(((Person)person).getName()).append("/");
        }
        return info.toString();
    }
}
