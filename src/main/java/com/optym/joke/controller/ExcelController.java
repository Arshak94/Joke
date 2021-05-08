package com.optym.joke.controller;

import com.optym.joke.excel.view.JokesExcelView;
import com.optym.joke.model.Joke;
import com.optym.joke.service.JokeService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class ExcelController {

    private final JokesExcelView jokesExcelView;

    private final JokeService jokeService;

    public ExcelController(JokesExcelView jokesExcelView, JokeService jokeService) {
        this.jokesExcelView = jokesExcelView;
        this.jokeService = jokeService;
    }

    @GetMapping(value = "/downloadXLS")
    public ResponseEntity<Resource> downloadXLS(@RequestParam(name = "category", required = false) String category,
                                                @RequestParam(name = "key") String key) {
        List<Joke> jokes = new ArrayList<>();
        String reportName = "jokes.xlsx";
        if (category.isEmpty()) {
            jokes = jokeService.search(key);
        } else {
            jokes = jokeService.advancedSearch(category, key);
        }
        byte[] bytes = jokesExcelView.buildExcelDocument(jokes);
        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("application", "force-download"));
        header.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + reportName + "\"");
        return new ResponseEntity<>(new ByteArrayResource(bytes),
                header, HttpStatus.CREATED);
    }
}
