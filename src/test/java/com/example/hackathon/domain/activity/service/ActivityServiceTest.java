package com.example.hackathon.domain.activity.service;

import com.example.hackathon.domain.activity.dto.ActivityNewRequestDto;
import com.example.hackathon.domain.activity.dto.ActivityResponseDto;
import com.example.hackathon.domain.activity.entity.Activity;
import com.example.hackathon.domain.activity.entity.RepeatCycle;
import com.example.hackathon.domain.activity.repository.ActivityRepository;
import com.example.hackathon.domain.category.entity.Category;
import com.example.hackathon.domain.category.entity.CategoryType;
import com.example.hackathon.domain.category.repository.CategoryRepository;
import com.example.hackathon.domain.user.entity.User;
import com.example.hackathon.domain.user.repository.UserRepository;
import com.example.hackathon.global.exception.BusinessException;
import com.example.hackathon.global.response.Code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ActivityServiceTest {

    @Mock private ActivityRepository activityRepository;
    @Mock private UserRepository userRepository;
    @Mock private CategoryRepository categoryRepository;

    @InjectMocks
    private ActivityService activityService;

    private Activity mockActivity;

    @BeforeEach
    void setup() {
        mockActivity = Activity.builder()
                .id(1L)
                .description("Test activity")
                .point(100)
                .sortOrder(1)
                .isCustom(false)
                .build();
    }

    @Test
    @DisplayName("카테고리별 활동 조회 성공")
    void getActivitiesByCategoryWithTodayFlags_success() {
        // given
        when(activityRepository.findByCategoryIdAndIsDisplayedTrueOrderBySortOrderAsc(1L))
                .thenReturn(List.of(mockActivity));

        List<Long> todayIds = List.of(1L);

        // when
        List<ActivityResponseDto> result = activityService.getActivitiesByCategoryWithTodayFlags(1L, todayIds);

        // then
        assertThat(result).hasSize(1);
        assertThat(result.get(0).isTodayActivity()).isTrue();
    }

    @Test
    @DisplayName("오늘의 미션 활동 조회 성공")
    void getTodayMissionActivitiesWithTodayFlags_success() {
        // given
        when(activityRepository.findByIsDisplayedTrueAndIsTodayActivityTrueOrderBySortOrderAsc())
                .thenReturn(List.of(mockActivity));

        List<Long> todayIds = List.of(1L);

        // when
        List<ActivityResponseDto> result = activityService.getTodayMissionActivitiesWithTodayFlags(todayIds);

        // then
        assertThat(result).hasSize(1);
        assertThat(result.get(0).isTodayActivity()).isTrue();
    }

    @Test
    @DisplayName("단일 활동 조회 - 존재할 경우")
    void getActivity_exists() {
        // given
        when(activityRepository.findById(1L)).thenReturn(Optional.of(mockActivity));

        // when
        ActivityResponseDto result = activityService.getActivity(1L);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
    }

    @Test
    @DisplayName("단일 활동 조회 - 존재하지 않을 경우 null 반환")
    void getActivity_notExists() {
        // given
        when(activityRepository.findById(1L)).thenReturn(Optional.empty());

        // when
        ActivityResponseDto result = activityService.getActivity(1L);

        // then
        assertThat(result).isNull();
    }

    @Test
    @DisplayName("사용자 활동 추가 성공")
    void addUserActivities_success() {
        // given
        User user = mock(User.class);
        Category category = mock(Category.class);
        ActivityNewRequestDto.AddActivity req = ActivityNewRequestDto.AddActivity.builder()
                .categoryType(CategoryType.CONSUMPTION)
                .description("설명")
                .title("제목")
                .repeatCycle(RepeatCycle.DAILY)
                .build();

        when(userRepository.findByKakaoId(1L)).thenReturn(Optional.of(user));
        when(categoryRepository.findByCategoryType(CategoryType.CONSUMPTION)).thenReturn(Optional.of(category));

        // when
//        activityService.addUserActivities(1L, req);

        // then
        verify(activityRepository, times(1)).save(any(Activity.class));
    }

    @Test
    @DisplayName("사용자 활동 추가 - 사용자 없음 예외")
    void addUserActivities_userNotFound() {
        // given
        ActivityNewRequestDto.AddActivity req = ActivityNewRequestDto.AddActivity.builder()
                .categoryType(CategoryType.CONSUMPTION)
                .build();

        when(userRepository.findByKakaoId(1L)).thenReturn(Optional.empty());

        // expect
//        assertThatThrownBy(() -> activityService.addUserActivities(1L, req))
//                .isInstanceOf(BusinessException.class)
//                .hasMessageContaining(Code.USER_NOT_FOUND.getMessage());
    }

    @Test
    @DisplayName("사용자 활동 추가 - 카테고리 없음 예외")
    void addUserActivities_categoryNotFound() {
        // given
        User user = mock(User.class);
        ActivityNewRequestDto.AddActivity req = ActivityNewRequestDto.AddActivity.builder()
                .categoryType(CategoryType.CONSUMPTION)
                .build();

        when(userRepository.findByKakaoId(1L)).thenReturn(Optional.of(user));
        when(categoryRepository.findByCategoryType(CategoryType.CONSUMPTION)).thenReturn(Optional.empty());

        // expect
//        assertThatThrownBy(() -> activityService.addUserActivities(1L, req))
//                .isInstanceOf(BusinessException.class)
//                .hasMessageContaining(Code.CATEGORY_NOT_FOUND.getMessage());
    }
}
