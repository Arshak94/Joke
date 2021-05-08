package com.optym.joke.service;

import com.optym.joke.dto.SearchElement;

import java.util.List;

public interface KeyNavigatorService {
    void addKeyNavigation(String key);

    List<SearchElement> searchedKey();

    void deleteDailyNavigations();
}
