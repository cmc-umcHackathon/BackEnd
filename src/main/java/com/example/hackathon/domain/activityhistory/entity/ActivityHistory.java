package com.example.hackathon.domain.activityhistory.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;


@Entity
@Table(name = "ACTIVITY_HISTORY")
@Getter
@Builder
public class ActivityHistory {

    @Id
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "USER_ID", nullable = false, length = 30)
    private Long userId;

    @Column(name = "CATEGORY_ID", nullable = false)
    private Long activityId;

    @Column(name = "POINT", nullable = false)
    private Integer point = 0;

    @Column(name = "ATTEND_STATUS", nullable = false)
    private Boolean attendStatus;

    @Column(name = "REG_DT", nullable = false)
    private LocalDateTime regDt;

    @Column(name = "REG_ID", nullable = false, length = 50)
    private String regId;

    @Column(name = "UPD_DT", nullable = false)
    private LocalDateTime updDt;

    @Column(name = "UPD_ID", length = 50)
    private String updId;

}
