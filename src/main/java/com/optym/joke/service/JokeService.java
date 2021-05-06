package com.optym.joke.service;

import com.optym.joke.dto.JokeScheduleDTO;
import com.optym.joke.model.Joke;

import java.util.List;

public interface JokeService {
    void create(JokeScheduleDTO jokeDTO);
    List<Joke> search(String key);
}
