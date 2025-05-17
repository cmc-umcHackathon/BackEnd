package com.example.hackathon.domain.activity.controller;

import com.example.hackathon.domain.activity.dto.ActivityNewRequestDto;
import com.example.hackathon.domain.activity.dto.ActivityRequestDto;
import com.example.hackathon.domain.activity.dto.ActivityResponseDto;
import com.example.hackathon.domain.activity.service.ActivityService;
import com.example.hackathon.global.auth.annotation.AuthUser;
import com.example.hackathon.global.response.Response;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activities")
@Validated
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

    @Operation(summary = "실천 사항 등록 API", description = "유저가 실천 사항을 등록합니다.")
    @PostMapping("/user/activities")
    public Response<Void> addUserActivities(@AuthUser Long userId, @RequestBody  ActivityNewRequestDto activityRequest) {
        activityService.addUserActivities(userId, activityRequest);

        return Response.ok();
    }
}
