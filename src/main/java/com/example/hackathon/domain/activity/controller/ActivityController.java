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

    // TODO 회원정보 추가해서 회원 이력 읽어야 함
    //  @GetMapping("/by-category")
    public Response<List<ActivityResponseDto>> getActivities(
            @AuthUser String userId,
            @RequestBody ActivityRequestDto requestDto
    ) {
        return Response.ok(
                activityParticipationFacade.getAvailableActivities(
                        userId,
                        requestDto.getCategoryId()
                )
        );
    }

    // TODO 오늘의 미션
    @GetMapping("/today-mission")
    public List<ActivityResponseDto> getTodayMissionActivities(@AuthUser String userId) {
        return activityParticipationFacade.getAvailableTodayMissionActivities(userId);
    }

    @Operation(summary = "실천 사항 등록 API", description = "유저가 실천 사항을 등록합니다.")
    @PostMapping("/user/activities")
    public Response<Void> addUserActivities(
            @AuthUser String userId,
            @RequestBody ActivityNewRequestDto.AddActivity activityRequest
    ) {
        activityService.addUserActivities(userId, activityRequest);
        return Response.ok();
    }
}
