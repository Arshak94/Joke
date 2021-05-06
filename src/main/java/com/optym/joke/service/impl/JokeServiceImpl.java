package com.optym.joke.service.impl;

import com.optym.joke.dto.JokeScheduleDTO;
import com.optym.joke.model.Joke;
import com.optym.joke.repository.JokeRepository;
import com.optym.joke.service.JokeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JokeServiceImpl implements JokeService {

    private final JokeRepository jokeRepository;

    public JokeServiceImpl(JokeRepository jokeRepository) {
        this.jokeRepository = jokeRepository;
    }

    @Override
    public void create(JokeScheduleDTO jokeDTO) {
     jokeRepository.save(fromDto(jokeDTO));
    }

    @Override
    public List<Joke> search(String key) {
        return jokeRepository.findBySetupOrPunchlineContaining(key, key);
    }

    private Joke fromDto(JokeScheduleDTO jokeScheduleDTO){
        return new Joke(jokeScheduleDTO.getId(), jokeScheduleDTO.getType(), jokeScheduleDTO.getSetup(), jokeScheduleDTO.getPunchline());
    }
}
