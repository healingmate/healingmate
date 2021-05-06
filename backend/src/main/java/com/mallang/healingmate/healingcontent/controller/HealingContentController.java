package com.mallang.healingmate.healingcontent.controller;

import com.mallang.healingmate.account.domain.Account;
import com.mallang.healingmate.common.security.CurrentAccount;
import com.mallang.healingmate.healingcontent.domain.HealingContent;
import com.mallang.healingmate.healingcontent.service.HealingContentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * com.mallang.healingmate.healingcontent.controller
 * healingContentController.java
 * @date    2021-05-05 오후 9:09
 * @author  서범석
 *
 * @변경이력
 **/

@RequiredArgsConstructor
@Tag(name = "healing-contents", description = "힐링 콘텐츠 관련 컨트롤러")
@RestController
@RequestMapping("/healing-content")
public class HealingContentController {

    private final HealingContentService healingContentService;

    @PostMapping("/content/bookmark/{contentId}")
    @Operation(summary = "힐링 콘텐츠 북마킹", description = "힐링 콘텐츠를 북마킹한다", security = @SecurityRequirement(name = "Authorization"))
    private ResponseEntity<Void> saveHealingContentBookmark(@PathVariable Long contentId, @Parameter(hidden = true) @CurrentAccount Account account){
        healingContentService.saveHealingContentBookmark(contentId, account);
        return ResponseEntity.noContent().build();
    }

    
    @GetMapping("/content/bookmarks")
    @Operation(summary = "사용자가 북마크한 힐링 콘텐츠 조회 ", description = "사용자가 북마크한 힐링 콘텐츠만 가져온다", security = @SecurityRequirement(name = "Authorization"))
    private ResponseEntity<List<HealingContent>> findHealingContent(@Parameter(hidden = true) @CurrentAccount Account account){
        return ResponseEntity.ok(healingContentService.findHealingContentBookmarks(account));
    }
    
    @DeleteMapping("/content/bookmark/{contentId}")
    @Operation(summary = "힐링 콘텐츠 북마킹 제거 ", description = "사용자가 북마크한 힐링 콘텐츠를 제거한다", security = @SecurityRequirement(name = "Authorization"))
    private ResponseEntity<Void> deleteHealingContentBookmark(@PathVariable Long contentId, @Parameter(hidden = true) @CurrentAccount Account account){
        healingContentService.deleteHealingContentBookmark(contentId, account);
        return ResponseEntity.noContent().build();
    }

}
