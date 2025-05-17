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

    @Operation(summary = "보유 포인트 및 실천 횟수 조회", description = "유저가 보유한 총 포인트와 실천 횟수를 조회합니다.")
    @GetMapping("/my/activity-info")
    public Response<Integer> getUserPoint(@AuthUser String userId) {
        Integer totalPoint = pointService.getUserTotalPoint(userId);
        return Response.ok(totalPoint);
    }

    @Operation(summary = "포인트 사용 API", description = "보유한 포인트를 사용합니다.")
    @PostMapping("/my/product")
    public Response<Void> usingPoint(@AuthUser String userId, @RequestBody PointRequestDTO.buyProductReq req) {
        pointService.usingPoint(userId, req);
        return Response.ok();
    }
}
