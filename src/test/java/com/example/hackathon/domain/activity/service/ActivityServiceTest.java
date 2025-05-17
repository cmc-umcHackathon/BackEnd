package com.example.hackathon.domain.activity.service;

import com.example.hackathon.domain.activity.dto.ActivityNewRequestDto;
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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ActivityServiceTest {

    @Mock private ActivityRepository activityRepository;
    @Mock private UserRepository userRepository;
    @Mock private CategoryRepository categoryRepository;

    @InjectMocks private ActivityService activityService;

    private User mockUser;
    private Category mockCategory;
    private Activity mockActivity;

    @BeforeEach
    void setUp() {
        mockUser = User.builder().id(1L).build();

        mockCategory = new Category();
        mockCategory.setId(1L);
        mockCategory.setCategoryType(CategoryType.CONSUMPTION);

        mockActivity = Activity.builder()
                .id(101L)
                .user(mockUser)
                .category(mockCategory)
                .title("Test Activity")
                .description("Desc")
                .sortOrder(1)
                .isCustom(true)
                .point(100)
                .build();
    }

    @Test
    void 카테고리별_활동_조회시_오늘의_활동_여부를_반영한다() {
        // given
        Long categoryId = 1L;
        List<Long> todayIds = List.of(101L);
        when(activityRepository.findByCategoryIdAndIsDisplayedTrueOrderBySortOrderAsc(categoryId))
                .thenReturn(List.of(mockActivity));

        // when
        var result = activityService.getActivitiesByCategoryWithTodayFlags(categoryId, todayIds);

        // then
        assertThat(result).hasSize(1);
        assertThat(result.get(0).isTodayActivity()).isTrue();
    }

    @Test
    void 오늘의_미션_활동_조회시_todayFlag를_반영한다() {
        // given
        when(activityRepository.findByIsDisplayedTrueAndIsTodayActivityTrueOrderBySortOrderAsc())
                .thenReturn(List.of(mockActivity));

        // when
        var result = activityService.getTodayMissionActivitiesWithTodayFlags(List.of(101L));

        // then
        assertThat(result).hasSize(1);
        assertThat(result.get(0).isTodayActivity()).isTrue();
    }

    @Test
    void 활동_추가_정상작동() {
        // given
        ActivityNewRequestDto.AddActivity request = new ActivityNewRequestDto.AddActivity();
        request.builder()
                        .categoryType(CategoryType.CONSUMPTION)
                                .title("Test Activity")
                                        .description("Desc")
                                                .repeatCycle(RepeatCycle.WEEKLY_1_2)
                                                        .build();

        when(userRepository.findById(1L)).thenReturn(Optional.of(mockUser));
        when(categoryRepository.findByCategoryType(CategoryType.CONSUMPTION)).thenReturn(Optional.of(mockCategory));

        // when
        activityService.addUserActivities("1L", request);

        // then
        verify(activityRepository, times(1)).save(any(Activity.class));
    }

    @Test
    void 활동_추가시_유저가_없으면_예외발생() {
        // given
        ActivityNewRequestDto.AddActivity request = new ActivityNewRequestDto.AddActivity();
        when(userRepository.findById("wrong-id")).thenReturn(Optional.empty());

        // expect
        assertThatThrownBy(() -> activityService.addUserActivities("wrong-id", request))
                .isInstanceOf(BusinessException.class)
                .hasMessageContaining(Code.USER_NOT_FOUND.getMessage());
    }

    @Test
    void 활동_추가시_카테고리가_없으면_예외발생() {
        // given
        ActivityNewRequestDto.AddActivity request = new ActivityNewRequestDto.AddActivity();
        request.setCategoryType("UNKNOWN");

        when(userRepository.findById("user-1")).thenReturn(Optional.of(mockUser));
        when(categoryRepository.findByCategoryType("UNKNOWN")).thenReturn(Optional.empty());

        // expect
        assertThatThrownBy(() -> activityService.addUserActivities("user-1", request))
                .isInstanceOf(BusinessException.class)
                .hasMessageContaining(Code.CATEGORY_NOT_FOUND.getMessage());
    }
}
