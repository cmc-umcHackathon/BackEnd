package com.example.hackathon.domain.activity.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Table(name = "ACTIVITY")
@Getter
public class Activity {

    @Id
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "DESCRIPTION", nullable = false, length = 30)
    private String description;

    @Column(name = "POINT", nullable = false)
    private Integer point = 0;

    @Column(name = "SORT_ORDER", nullable = false)
    private Integer sortOrder = 1;

    @Column(name = "CATEGORY_ID", nullable = false)
    private Long categoryId;

    @Column(name = "IS_DISPLAYED", nullable = false)
    private Boolean isDisplayed;

    @Column(name = "IS_TODAY_ACTIVITY", nullable = false)
    private Boolean isTodayActivity;

    @Column(name = "IS_CUSTOM", nullable = false)
    private Boolean isCustom;

    @Column(name = "REG_DT", nullable = false)
    private LocalDateTime regDt;

    @Column(name = "REG_ID", nullable = false, length = 50)
    private String regId;

    @Column(name = "UPD_DT", nullable = false)
    private LocalDateTime updDt;

    @Column(name = "UPD_ID", length = 50)
    private String updId;

    // Getters, Setters 생략 가능 (Lombok 사용 시 @Data)


}
