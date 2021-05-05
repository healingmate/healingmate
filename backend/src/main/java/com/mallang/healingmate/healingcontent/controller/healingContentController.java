package com.mallang.healingmate.healingcontent.controller;

import com.mallang.healingmate.account.domain.Account;
import com.mallang.healingmate.common.security.CurrentAccount;
import com.mallang.healingmate.healingcontent.service.HealingContentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Tag(name = "healing-contents", description = "힐링 콘텐츠 관련 컨트롤러")
@RestController
@RequestMapping("/healing-content/content")
public class healingContentController {

    private final HealingContentService healingContentService;

    @PostMapping("/bookmark/{contentId}")
    @Operation(summary = "힐링 콘텐츠 북마킹", description = "힐링 콘텐츠에서 콘텐츠를 북마킹한다", security = @SecurityRequirement(name = "Authorization"))
    private ResponseEntity<Void> saveHealingContent(@PathVariable Long contentId, @Parameter(hidden = true) @CurrentAccount Account account){
        healingContentService.saveHealingContent(contentId, account);
        return ResponseEntity.noContent().build();
    }
}
