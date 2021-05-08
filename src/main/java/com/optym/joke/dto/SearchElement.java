package com.optym.joke.dto;

public class SearchElement {

    private int count;
    private String key;

    public SearchElement() {
    }

    public SearchElement(int count, String key) {
        this.count = count;
        this.key = key;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
