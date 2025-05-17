package com.example.hackathon.domain.point.service;


import com.example.hackathon.domain.point.repository.PointRepository;
import com.example.hackathon.domain.point.entity.Point;
import org.springframework.stereotype.Service;


@Service
public class PointService {

    private final PointRepository pointRepository;

    public PointService(PointRepository pointRepository) {
        this.pointRepository = pointRepository;
    }

    public Integer getUserTotalPoint(String userId) {
        return pointRepository.findByUserId(userId)
                .map(Point::getTotalPoint)
                .orElse(0);       // 포인트 정보가 없는 경우 0 반환
    }
}
