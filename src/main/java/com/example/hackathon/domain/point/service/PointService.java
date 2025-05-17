package com.example.hackathon.domain.point.service;


import com.example.hackathon.domain.point.dto.PointRequestDTO;
import com.example.hackathon.domain.point.repository.PointRepository;
import com.example.hackathon.domain.point.entity.Point;
import com.example.hackathon.domain.user.repository.UserRepository;
import com.example.hackathon.global.exception.BusinessException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.example.hackathon.global.response.Code;


@Service
public class PointService {

    private final PointRepository pointRepository;
    private final UserRepository userRepository;

    public PointService(PointRepository pointRepository, UserRepository userRepository) {
        this.pointRepository = pointRepository;
        this.userRepository = userRepository;
    }

    public Integer getUserTotalPoint(Long userId) {
        return pointRepository.findByUserId(userId)
                .map(Point::getTotalPoint)
                .orElse(0);       // 포인트 정보가 없는 경우 0 반환
    }

    @Transactional
    public void usingPoint(Long userId, PointRequestDTO.buyProductReq req){

        Point point = pointRepository.findByUserId(userId)
                .orElseThrow(() -> new BusinessException(Code.POINT_NOT_FOUND, "이미 오늘 참여한 활동입니다."));

        int productPrice = req.getProductType().getPoint();
        int userPoints = point.getTotalPoint();

        if (userPoints < productPrice) { throw new BusinessException(Code.POINT_NOT_ENOUGH, "이미 오늘 참여한 활동입니다.");}

        point.setTotalPoint(userPoints - productPrice);
    }
}
