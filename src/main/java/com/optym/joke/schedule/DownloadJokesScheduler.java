package com.optym.joke.schedule;

import com.optym.joke.dto.JokeScheduleDTO;
import com.optym.joke.service.JokeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@EnableAsync
public class DownloadJokesScheduler {
    private static final String JOKES_URL = "https://official-joke-api.appspot.com/jokes/random";
    private static final Logger LOGGER = LoggerFactory.getLogger(DownloadJokesScheduler.class);

    private final RestTemplate restTemplate;

    private final JokeService jokeService;

    public DownloadJokesScheduler(RestTemplate restTemplate, JokeService jokeService) {
        this.restTemplate = restTemplate;
        this.jokeService = jokeService;
    }

    @Async
    @Scheduled(fixedDelay = 8 * 1000, initialDelay = 1000)
    public void downloadJoke(){
        LOGGER.info("START schedule");
        JokeScheduleDTO joke = null;
        try {
            joke = restTemplate.getForObject(JOKES_URL, JokeScheduleDTO.class);
            jokeService.create(joke);
        } catch (Exception e){
            LOGGER.warn("Scheduler must be wait 15 minutes");
        }
        LOGGER.info("END schedule");
    }
}
