package com.mallang.healingmate.account.domain;

import com.mallang.healingmate.healingcontent.domain.AccountHealingContents;
import com.mallang.healingmate.image.domain.Image;
import com.mallang.healingmate.keyword.domain.AccountKeywords;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * com.mallang.healingmate.account.domain
 * Account.java
 *
 * @author 서범석, 이아영
 * @date 2021-04-21 오후 2:19
 * @변경이력
 **/

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private UserRole role = UserRole.ROLE_USER;

    @Column(unique = true)
    private String userId;

    private String password;

    @Column(unique = true)
    private String nickname;

    private Integer birthYear;

    @OneToOne
    @JoinColumn(name = "image_id")
    private Image image;

    private Integer score;

    @CreationTimestamp
    private LocalDateTime createDate;

    @UpdateTimestamp
    private LocalDateTime updateDate;

    @Embedded
    private AccountKeywords accountKeywords;

    @Embedded
    private AccountHealingContents accountHealingContents;

    @Builder
    public Account(UserRole role, String userId, String password, String nickname, Image image, Integer birthYear, Integer score) {
        this.role = role;
        this.userId = userId;
        this.password = password;
        this.nickname = nickname;
        this.image = image;
        this.birthYear = birthYear;
        this.score = score;
    }
}
