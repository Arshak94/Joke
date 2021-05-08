package com.optym.joke.repository;

import com.optym.joke.model.Joke;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JokeRepository extends JpaRepository<Joke, Long> {
    List<Joke> findBySetupOrPunchlineContaining(String setup, String punchline);
    List<Joke> findByType(String type);
}
