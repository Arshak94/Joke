package com.optym.joke.service;

import com.optym.joke.dto.JokeScheduleDTO;
import com.optym.joke.model.Joke;

import java.util.List;
import java.util.Set;

public interface JokeService {
    void create(JokeScheduleDTO jokeDTO);
    List<Joke> search(String key);
    Set<String> getCategories();
    List<Joke> advancedSearch( String category, String key);
}
