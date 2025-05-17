package com.example.hackathon.domain.activity.entity;

import com.example.hackathon.domain.category.entity.Category;
import com.example.hackathon.domain.user.entity.User;
import com.example.hackathon.global.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Entity
@Table(name = "ACTIVITY")
@Getter
@Builder
public class Activity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @Column(name = "DESCRIPTION", nullable = false, length = 30)
    private String description;

    @Builder.Default
    @Column(name = "POINT", nullable = false)
    private Integer point = 0;

    @Builder.Default
    @Column(name = "SORT_ORDER", nullable = false)
    private Integer sortOrder = 1;

    @Column(name = "CATEGORY_ID", nullable = false)
    private Long categoryId;

    @Enumerated(EnumType.STRING) // enum을 문자열로 저장
    @Column(name="CATEGORY_TYPE")
    private RepeatCycle repeatCycle;

    @Column(name = "IS_DISPLAYED", nullable = false)
    private Boolean isDisplayed;

    @Column(name = "IS_TODAY_ACTIVITY", nullable = false)
    private Boolean isTodayActivity;

    @Column(name = "IS_CUSTOM", nullable = false)
    private Boolean isCustom;

    @Column(name = "REG_ID", nullable = false, length = 50)
    private String regId;

    @Column(name = "UPD_ID", length = 50)
    private String updId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
}
