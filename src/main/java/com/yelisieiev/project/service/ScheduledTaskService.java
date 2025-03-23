package com.yelisieiev.project.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledTaskService {
    @Scheduled(fixedRate = 60000) 
    public void logTask() {
        System.out.println("Тема варіанту 11: Використання Spring AOP та Spring Mail");
    }
}
