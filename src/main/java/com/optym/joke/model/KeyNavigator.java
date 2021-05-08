package com.optym.joke.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "key_navigator")
public class KeyNavigator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String searchKey;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    private int count;
    public KeyNavigator() {
    }

    public KeyNavigator(Long id, String key, Date date, int count) {
        this.id = id;
        this.searchKey = key;
        this.date =date;
        this.count = count;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKey() {
        return searchKey;
    }

    public void setKey(String key) {
        this.searchKey = key;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
