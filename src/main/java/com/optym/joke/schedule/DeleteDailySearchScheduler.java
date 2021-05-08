package com.optym.joke.schedule;

import com.optym.joke.service.KeyNavigatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableAsync
public class DeleteDailySearchScheduler {
    private static final Logger LOGGER = LoggerFactory.getLogger(DeleteDailySearchScheduler.class);

    private final KeyNavigatorService keyNavigatorService;

    public DeleteDailySearchScheduler(KeyNavigatorService keyNavigatorService) {
        this.keyNavigatorService = keyNavigatorService;
    }

    @Async
    @Scheduled(cron = "0 00 23 * * ?")
    public void deleteDaySearches(){
        LOGGER.info("START schedule");
        keyNavigatorService.deleteDailyNavigations();
        LOGGER.info("END schedule");
    }
}
