package com.optym.joke.service.impl;

import com.optym.joke.dto.JokeScheduleDTO;
import com.optym.joke.model.Joke;
import com.optym.joke.repository.JokeRepository;
import com.optym.joke.service.JokeService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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

    @Override
    public Set<String> getCategories() {
        Set<String> categories = new HashSet<>();
        jokeRepository.findAll().forEach(
                x-> categories.add(x.getType())
        );
        return categories;
    }

    @Override
    public List<Joke> advancedSearch(String category, String key) {
        return jokeRepository.findByType(category).stream().filter(x-> Pattern.compile(Pattern.quote(key), Pattern.CASE_INSENSITIVE).matcher(x.getPunchline()).find() || Pattern.compile(Pattern.quote(key), Pattern.CASE_INSENSITIVE).matcher(x.getSetup()).find()).collect(Collectors.toList());
    }

    private Joke fromDto(JokeScheduleDTO jokeScheduleDTO){
        return new Joke(jokeScheduleDTO.getId(), jokeScheduleDTO.getType(), jokeScheduleDTO.getSetup(), jokeScheduleDTO.getPunchline());
    }
}
