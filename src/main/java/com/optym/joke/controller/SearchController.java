package com.optym.joke.controller;

import com.optym.joke.model.Joke;
import com.optym.joke.service.JokeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SearchController {

    private final JokeService jokeService;

    public SearchController(JokeService jokeService) {
        this.jokeService = jokeService;
    }

    @GetMapping()
    public String show() {
        return "index";
    }
    @GetMapping("/simple-search")
    public String simpleSearch(@RequestParam(name = "key") String key, Model model){
       List<Joke> jokes = jokeService.search(key);
       model.addAttribute("searchList", jokes);
       return "index";
    }
}
