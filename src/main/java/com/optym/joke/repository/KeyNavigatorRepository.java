package com.optym.joke.repository;

import com.optym.joke.model.KeyNavigator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KeyNavigatorRepository extends JpaRepository<KeyNavigator, Long> {
    KeyNavigator findBySearchKey(String key);
}
