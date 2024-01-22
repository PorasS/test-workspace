package com.example.test.gitplugin.testDto;

import com.fasterxml.jackson.annotation.JsonProperty;


public class TestJson {
    private int id;
    private String name;
    @JsonProperty("is_good")
    private boolean isGood;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isGood() {
        return isGood;
    }

    public void setGood(boolean good) {
        isGood = good;
    }

    @Override
    public String toString() {
        return "TestJson{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isGood=" + isGood +
                '}';
    }
}
