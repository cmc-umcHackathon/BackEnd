package com.example.hackathon.domain.user.entity;

import com.example.hackathon.domain.user.enums.OAuthProvider;
import com.example.hackathon.global.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "USERS")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    @Comment("회원 고유값")
    private Long id;

    @Column(name = "NAME", nullable = false, length = 50)
    @Comment("이름")
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "PROVIDER", nullable = false, length = 10)
    @Comment("소셜 로그인 제공자")
    private OAuthProvider oauthProvider;


    @Column(name = "REG_ID", nullable = false, length = 50)
    @Comment("등록자")
    private String regId;


    @Column(name = "UPD_ID", length = 50)
    @Comment("수정자")
    private String updId;
}
