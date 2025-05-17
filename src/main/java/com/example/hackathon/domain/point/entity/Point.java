package com.example.hackathon.domain.point.entity;

import com.example.hackathon.domain.user.entity.User;
import com.example.hackathon.global.BaseEntity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "Point")
public class Point extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "point_id", nullable = false)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private Integer totalPoint;    // 총 포인트량

    // Getter
    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Integer getTotalPoint() {
        return totalPoint;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setTotalPoint(Integer totalPoint) {
        this.totalPoint = totalPoint;
    }

}
