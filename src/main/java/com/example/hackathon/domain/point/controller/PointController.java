package com.example.hackathon.domain.point.controller;


import com.example.hackathon.domain.point.dto.PointRequestDTO;
import com.example.hackathon.domain.point.service.PointService;
import com.example.hackathon.global.auth.annotation.AuthUser;
import com.example.hackathon.global.response.Response;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Validated
public class PointController {

    private final PointService pointService;

    public PointController(PointService pointService) {
        this.pointService = pointService;
    }

    @Operation(summary = "총 보유 포인트 조회", description = "유저가 보유한 총 포인트를 조회합니다.")
    @GetMapping("/my/activity-info")
    public Response<Integer> getUserPoint(@AuthUser Long userId) {
        Integer totalPoint = pointService.getUserTotalPoint(userId);
        return Response.ok(totalPoint);
    }

}
