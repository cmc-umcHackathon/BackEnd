package com.example.hackathon.domain.point.service;

import com.example.hackathon.domain.point.dto.PointRequestDTO;
import com.example.hackathon.domain.point.entity.Point;
import com.example.hackathon.domain.point.entity.ProductType;
import com.example.hackathon.domain.point.repository.PointRepository;
import com.example.hackathon.domain.user.entity.User;
import com.example.hackathon.domain.user.repository.UserRepository;
import com.example.hackathon.global.exception.BusinessException;
import com.example.hackathon.global.response.Code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PointServiceV1Test {

    @Mock
    private PointRepository pointRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private PointService pointService;

    @Mock
    private User user;

    private Point mockPoint;

    Long userId = 1L;

    @BeforeEach
    void setUp() {
        mockPoint = new Point();
        mockPoint.setUser(user);
        mockPoint.setTotalPoint(500);
    }

    @Test
    @DisplayName("사용자 포인트 조회 - 성공 (500 포인트)")
    void getUserTotalPoint_success() {
        // given
        when(pointRepository.findByUserId(userId)).thenReturn(Optional.of(mockPoint));

        // when
        int totalPoint = pointService.getUserTotalPoint(userId);

        // then
        assertThat(totalPoint).isEqualTo(500);
    }

    @Test
    @DisplayName("사용자 포인트 조회 - 정보 없을 경우 0 반환")
    void getUserTotalPoint_zeroWhenNotExists() {
        // given
        when(pointRepository.findByUserId(userId)).thenReturn(Optional.empty());

        // when
        int totalPoint = pointService.getUserTotalPoint(userId);

        // then
        assertThat(totalPoint).isEqualTo(0);
    }

    @Test
    @DisplayName("포인트 사용 - 성공")
    void usingPoint_success() {
        // given
        PointRequestDTO.buyProductReq req = PointRequestDTO.buyProductReq.builder()
                .productType(ProductType.KEYRING) // 예: KEYRING은 200포인트 필요
                .build();

        when(pointRepository.findByUserId(userId)).thenReturn(Optional.of(mockPoint));

        // when
        pointService.usingPoint(userId, req);

        // then
        assertThat(mockPoint.getTotalPoint()).isEqualTo(279); // 500 - 200
    }

    @Test
    @DisplayName("포인트 사용 - 잔여 포인트 부족 시 예외 발생")
    void usingPoint_notEnoughPoint_throwsException() {
        // given
        PointRequestDTO.buyProductReq req = PointRequestDTO.buyProductReq.builder()
                .productType(ProductType.KEYRING) // 예: 키링
                .build();

        when(pointRepository.findByUserId(userId)).thenReturn(Optional.of(mockPoint));

        // expect
        assertThatThrownBy(() -> pointService.usingPoint(userId, req))
                .isInstanceOf(BusinessException.class)
                .hasMessageContaining(Code.POINT_NOT_ENOUGH.getMessage());
    }

    @Test
    @DisplayName("포인트 사용 - 사용자 포인트 정보가 없을 시 예외 발생")
    void usingPoint_pointEntityNotFound_throwsException() {
        // given
        PointRequestDTO.buyProductReq req = PointRequestDTO.buyProductReq.builder()
                .productType(ProductType.KEYRING)
                .build();

        when(pointRepository.findByUserId(userId)).thenReturn(Optional.empty());

        // expect
        assertThatThrownBy(() -> pointService.usingPoint(userId, req))
                .isInstanceOf(BusinessException.class)
                .hasMessageContaining(Code.POINT_NOT_FOUND.getMessage());
    }
}
