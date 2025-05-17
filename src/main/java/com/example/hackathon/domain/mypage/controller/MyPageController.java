package com.example.hackathon.domain.mypage.controller;


import com.example.hackathon.global.response.Response;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Validated
public class MyPageController {

    @Operation(summary = "보유 포인트 조회", description = "유저가 보유한 포인트를 반환합니다.")
    @GetMapping("/my/point")
    public Response<Void> getUserPoint(){
        return null;
    }


    @Operation(summary = "실천 이력 조회", description = "실천 내역, 포인트, 실시 날짜를 반환합니다.")
    @GetMapping("/my/activities")
    public Response<Void> getUserActivityHistory(){
        return null;
    }


    @Operation(summary = "실천 사항 등록", description = "제목, 카테고리, 설명, 실천 주기를 통해 실천 사항을 등록합니다.")
    @PostMapping("/my/activity")
    public Response<Void> addUserActivity(){
        return null;
    }

}
