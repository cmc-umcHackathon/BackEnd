package com.example.hackathon.domain.activity.controller;

import com.example.hackathon.domain.activity.dto.ActivityRequestDto;
import com.example.hackathon.domain.activity.dto.ActivityResponseDto;
import com.example.hackathon.domain.activity.service.ActivityService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activities")
public class ActivityController {

    private final ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @GetMapping("/by-category")
    public List<ActivityResponseDto> getActivities(@RequestBody ActivityRequestDto requestDto) {
        return activityService.getValidActivitiesByCategory(requestDto);
    }

    @GetMapping("/by-category")
    public List<ActivityResponseDto> getActivitiesddd(@RequestBody ActivityRequestDto requestDto) {
        return activityService.getValidActivitiesByCategory(requestDto);
    }
}
