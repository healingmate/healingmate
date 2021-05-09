package com.mallang.healingmate.account.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mallang.healingmate.article.domain.AccountArticles;
import com.mallang.healingmate.article.domain.Article;
import com.mallang.healingmate.healingcontent.domain.AccountHealingContent;
import com.mallang.healingmate.healingcontent.domain.AccountHealingContents;
import com.mallang.healingmate.healingcontent.domain.HealingContent;
import com.mallang.healingmate.image.domain.Image;
import com.mallang.healingmate.keyword.domain.AccountKeyword;
import com.mallang.healingmate.keyword.domain.AccountKeywords;
import com.mallang.healingmate.keyword.domain.Keyword;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * com.mallang.healingmate.account.domain
 * Account.java
 *
 * @author 서범석, 이아영
 * @date 2021-04-21 오후 2:19
 * @변경이력 21-05-04 프로필 이미지를 Image 테이블이 아닌 Enum값으로 관리하도록 결정됨에 따라 변경
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
    private UserRole role;

    @Column(unique = true)
    private String userId;

    private String password;

    @Column(unique = true)
    private String nickname;

    private Integer birthYear;

    @Enumerated(EnumType.STRING)
    private ProfileImage profileImage;

    private Integer score;

    @CreationTimestamp
    private LocalDateTime createdDate;

    @UpdateTimestamp
    private LocalDateTime updatedDate;

    @Embedded
    private AccountKeywords accountKeywords = AccountKeywords.empty();

    @Embedded
    private AccountHealingContents accountHealingContents = AccountHealingContents.empty();

    @Embedded
    private AccountArticles accountArticles;

    @Builder
    public Account(UserRole role, String userId, String password, String nickname, ProfileImage profileImage, Integer birthYear, Integer score) {
        this.role = role;
        this.userId = userId;
        this.password = password;
        this.nickname = nickname;
        this.profileImage = profileImage;
        this.birthYear = birthYear;
        this.score = score;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void update(Account requestAccount, List<Keyword> keywords) {
        updateAccountKeywords(keywords);

        this.nickname = requestAccount.nickname;
        this.profileImage = requestAccount.profileImage;
    }

    public void updateAccountKeywords(List<Keyword> keywords) {
        this.accountKeywords.clear();
        for (Keyword keyword : keywords) {
            this.accountKeywords.add(AccountKeyword.associate(this, keyword));
        }
    }

    public void addAccountHealingContents(HealingContent healingContent) {
        this.accountHealingContents.add(AccountHealingContent.associate(this, healingContent));
    }

    public List<String> getKeywords() {
        return accountKeywords.getKeywords();
    }

    public List<HealingContent> getHealingContents() {
        return accountHealingContents.getHealingContents();
    }
}
