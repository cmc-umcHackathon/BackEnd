package com.example.hackathon.domain.activityhistory.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import com.example.hackathon.domain.activityhistory.repository.ActivityHistoryRepository;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ActivityHistoryServiceTest {

    @Mock
    private ActivityHistoryRepository activityHistoryRepository;

    @InjectMocks
    private ActivityHistoryService activityHistoryService;

    private Long userId;
    private LocalDate today;
    Long activityId;

    @BeforeEach
    void setUp() {
        userId = 10L;
        activityId = 2L;
        today = LocalDate.now();

    }

    @Test
    @DisplayName("사용자의_오늘_출석한_활동ID를_조회한다")
    void getTodayActivityIdsByUser() {
        // given
        List<Long> expectedActivityIds = Arrays.asList(101L, 102L, 103L);
        when(activityHistoryRepository.findTopByUserIdAndActivityIdAndTodayOrderByRegDtDesc(userId, activityId, today));

        // when
        List<Long> actualActivityIds = activityHistoryService.getTodayActivityIdsByUser(userId);

        // then
        assertThat(actualActivityIds).isEqualTo(expectedActivityIds);
        verify(activityHistoryRepository, times(1)).findTopByUserIdAndActivityIdAndTodayOrderByRegDtDesc(userId,activityId, today);
    }
}
