package com.example.cryptotrading.service;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.net.URISyntaxException;

public interface CronJobService {
    void triggerNewDataChanges() throws URISyntaxException, JsonProcessingException;
}
