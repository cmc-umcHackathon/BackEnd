package com.example.hackathon.domain.activity.controller;

import com.example.hackathon.domain.activity.dto.ActivityNewRequestDto;
import com.example.hackathon.domain.activity.dto.ActivityRequestDto;
import com.example.hackathon.domain.activity.dto.ActivityResponseDto;
import com.example.hackathon.domain.activity.facade.ActivityParticipationFacade;
import com.example.hackathon.domain.activity.service.ActivityService;
import com.example.hackathon.global.auth.annotation.AuthUser;
import com.example.hackathon.global.response.Response;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activities")
@Validated
public class ActivityController {

    private final ActivityParticipationFacade activityParticipationFacade;
    private final ActivityService activityService;

    public ActivityController(ActivityParticipationFacade activityParticipationFacade, ActivityService activityService) {
        this.activityParticipationFacade = activityParticipationFacade;
        this.activityService = activityService;
    }

    @Operation(summary = "현재 유효한 활동 조회 API", description = "현재 고객의 이력에 기반한 활동 정보를 조회합니다.")
    @GetMapping("/by-category")
    public Response<List<ActivityResponseDto>> getActivities(
            @AuthUser Long userId,
            @RequestBody ActivityRequestDto requestDto
    ) {
        return Response.ok(
                activityParticipationFacade.getAvailableActivities(
                        userId,
                        requestDto.getCategoryId()
                )
        );
    }

    @Operation(summary = "현재 유효한 오늘의 미션조회 API", description = "현재 고객의 이력에 기반한 오늘의 미션 정보를 조회합니다.")
    @GetMapping("/today-mission")
    public List<ActivityResponseDto> getTodayMissionActivities(@AuthUser Long userId) {
        return activityParticipationFacade.getAvailableTodayMissionActivities(userId);
    }

    @PostMapping("/user/activities")
    public Response<Void> addUserActivities(
            @AuthUser Long userId,
            @RequestBody ActivityNewRequestDto.AddActivity activityRequest
    ) {
        activityService.addUserActivities(userId, activityRequest);
        return Response.ok();
    }
}
