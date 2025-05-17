package com.example.hackathon.domain.activityhistory.controller;

import com.example.hackathon.domain.activityhistory.dto.ActivityHistoryRequestDto;
import com.example.hackathon.domain.activityhistory.facade.ActivityPointGrantFacade;
import com.example.hackathon.domain.activityhistory.service.ActivityHistoryService;
import com.example.hackathon.global.auth.annotation.AuthUser;
import com.example.hackathon.global.response.Response;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/activity-history")
public class ActivityHistoryController {

    private final ActivityPointGrantFacade activityPointGrantFacade;
    private final ActivityHistoryService activityHistoryService;

    public ActivityHistoryController(
            ActivityPointGrantFacade activityPointGrantFacade,
            ActivityHistoryService activityHistoryService
    ) {
        this.activityPointGrantFacade = activityPointGrantFacade;
        this.activityHistoryService = activityHistoryService;
    }

    @Operation(summary = "활동 참여 이력 횟수 조회 API", description = "현재 고객의 활동 참여 횟수를 조회합니다.")
    @GetMapping("/")
    public Response<Long> getTotalActivityHistoryCount(@AuthUser Long userId) {
        return Response.ok(activityHistoryService.getTotalActivityHistoryCount(userId));
    }

    @Operation(summary = "활동 참여 이력 저장 API", description = "현재 고객의 활동 참여 이력을 저장합니다.")
    @PostMapping
    public Response<Void> recordActivityHistory(
            @AuthUser Long userId,
            @RequestBody ActivityHistoryRequestDto requestDto
    ) {
        activityPointGrantFacade.grantParticipationAndPoint(userId, requestDto);
        return Response.ok();
    }

}
