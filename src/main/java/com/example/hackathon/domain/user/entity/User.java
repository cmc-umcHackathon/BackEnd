package com.example.hackathon.domain.user.entity;

import com.example.hackathon.domain.user.enums.OAuthProvider;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "USER")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class  User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    @Comment("회원 고유값")
    private Long id;

    @Column(name = "NAME", nullable = false, length = 50)
    @Comment("이름")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "PROVIDER", nullable = false, length = 10)
    @Comment("소셜 로그인 제공자")
    private OAuthProvider oauthProvider;

    @CreatedDate
    @Column(name = "REG_DT", nullable = false)
    @Comment("등록일")
    private LocalDateTime regDt;

    @Column(name = "REG_ID", nullable = false, length = 50)
    @Comment("등록자")
    private String regId;

    @LastModifiedDate
    @Column(name = "UPD_DT", nullable = false)
    @Comment("수정일")
    private LocalDateTime updDt;

    @Column(name = "UPD_ID", length = 50)
    @Comment("수정자")
    private String updId;
}
