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
class PointServiceTest {

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
    @DisplayName("포인트_조회_성공")
    void getUserTotalPoint() {
        // given
        when(pointRepository.findByUserId(userId)).thenReturn(Optional.of(mockPoint));

        // when
        int totalPoint = pointService.getUserTotalPoint(userId);

        // then
        assertThat(totalPoint).isEqualTo(500);
    }

    @Test
    void 포인트_정보_없을때_0_반환() {
        // given
        when(pointRepository.findByUserId(userId)).thenReturn(Optional.empty());

        // when
        int totalPoint = pointService.getUserTotalPoint(userId);

        // then
        assertThat(totalPoint).isEqualTo(0);
    }

    @Test
    void 포인트_사용_성공() {
        // given
        when(pointRepository.findByUserId(userId)).thenReturn(Optional.of(mockPoint));

        // when
        pointService.updatePointBalance(userId, 200);

        // then
        assertThat(mockPoint.getTotalPoint()).isEqualTo(300);
    }

    @Test
    void 포인트_부족_예외() {
        // given
        when(pointRepository.findByUserId(userId)).thenReturn(Optional.of(mockPoint));

        // expect
        assertThatThrownBy(() -> pointService.updatePointBalance(userId, 1000))
                .isInstanceOf(BusinessException.class)
                .hasMessageContaining(Code.POINT_NOT_ENOUGH.getMessage());
    }
}
