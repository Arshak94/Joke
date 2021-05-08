package com.optym.joke.controller;

import com.optym.joke.dto.SearchElement;
import com.optym.joke.model.Joke;
import com.optym.joke.service.JokeService;
import com.optym.joke.service.KeyNavigatorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
public class SearchController {

    private final JokeService jokeService;
    private final KeyNavigatorService keyNavigatorService;

    public SearchController(JokeService jokeService, KeyNavigatorService keyNavigatorService) {
        this.jokeService = jokeService;
        this.keyNavigatorService = keyNavigatorService;
    }

    @GetMapping()
    public ModelAndView show() {
        ModelAndView modelAndView = new ModelAndView("index");
        return modelAndView;
    }

    @GetMapping("/simple-search")
    public ModelAndView simpleSearch(@RequestParam(name = "key") String key) {
        List<Joke> jokes = new ArrayList<>();
        jokes = jokeService.search(key);
        keyNavigatorService.addKeyNavigation(key);
        ModelAndView modelAndView = new ModelAndView("result");
        modelAndView.addObject("key", key);
        modelAndView.addObject("isSimple", true);
        modelAndView.addObject("searchList", jokes);
        return modelAndView;
    }

    @GetMapping("/advanced")
    public ModelAndView advancedSearch() {

        ModelAndView modelAndView = new ModelAndView("advancedSearch");
        Set<String> categories = jokeService.getCategories();
        List<SearchElement> searchElements = keyNavigatorService.searchedKey();
        modelAndView.addObject("categories", categories);
        modelAndView.addObject("searchedElements", searchElements);
        return modelAndView;
    }


    @GetMapping("/advanced-search")
    public ModelAndView advancedSearch(@RequestParam(name = "key") String key,
                                       @RequestParam(name = "category") String category) {
        List<Joke> jokes = new ArrayList<>();

        jokes = jokeService.advancedSearch(category, key);
        keyNavigatorService.addKeyNavigation(key);

        ModelAndView modelAndView = new ModelAndView("result");
        modelAndView.addObject("category", category);
        modelAndView.addObject("key", key);
        modelAndView.addObject("searchList", jokes);
        return modelAndView;
    }
}
