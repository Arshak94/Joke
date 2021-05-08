package com.optym.joke.service.impl;

import com.optym.joke.dto.SearchElement;
import com.optym.joke.model.KeyNavigator;
import com.optym.joke.repository.KeyNavigatorRepository;
import com.optym.joke.service.KeyNavigatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class KeyNavigatorServiceImpl implements KeyNavigatorService {

    @Autowired
    private KeyNavigatorRepository keyNavigatorRepository;

    @Override
    public void addKeyNavigation(String key) {
        KeyNavigator keyNavigator = new KeyNavigator();
        KeyNavigator byKey = keyNavigatorRepository.findBySearchKey(key);
        if (byKey!=null){
            byKey.setDate(new Date());
            byKey.setCount(byKey.getCount()+1);
            keyNavigatorRepository.save(byKey);
        } else {
            keyNavigator.setCount(1);
            keyNavigator.setDate(new Date());
            keyNavigator.setKey(key);
            keyNavigatorRepository.save(keyNavigator);
        }
    }

    @Override
    public List<SearchElement> searchedKey() {
        return keyNavigatorRepository.findAll().stream()
                .map(x -> new SearchElement(x.getCount(),x.getKey())).collect(Collectors.toList());

    }

    @Transactional
    @Override
    public void deleteDailyNavigations() {
        keyNavigatorRepository.deleteAll();
    }
}
