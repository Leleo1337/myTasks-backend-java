package com.leleo.mytasks.controllers;

import com.leleo.mytasks.services.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/stats")
public class StatsController {

    @Autowired
    private StatsService statsService;

    @GetMapping
    public Map<String, Long> getStats(){
        Map<String, Long> stats = statsService.getTaskStats();
        return stats;
    }

}
