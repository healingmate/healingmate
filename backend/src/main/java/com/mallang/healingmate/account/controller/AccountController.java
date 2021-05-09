package com.mallang.healingmate.account.controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mallang.healingmate.account.domain.Account;
import com.mallang.healingmate.account.dto.request.*;
import com.mallang.healingmate.account.dto.response.AuthResponse;
import com.mallang.healingmate.account.dto.response.RefreshResponse;
import com.mallang.healingmate.account.service.AccountService;
import com.mallang.healingmate.common.exception.ErrorResponse;
import com.mallang.healingmate.common.security.CurrentAccount;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * com.mallang.healingmate.account.controller
 * AccountController.java
 *
 * @author 이아영
 * @date 2021-04-28 오전 10:00
 * @변경이력
 **/

@Tag(name = "Account", description = "사용자 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    @Operation(summary = "회원가입", description = "회원 정보를 전달 받아 저장합니다.", responses = {
            @ApiResponse(responseCode = "201", description = "회원 가입 성공", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "400", description = "잘못된 요청", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "409", description = "아이디또는 닉네임 중복", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "서버 오류", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<String> signup(@Valid @RequestBody SignupRequest signupRequest) {
        accountService.createAcount(signupRequest);
        return new ResponseEntity<String>("Created", HttpStatus.CREATED);
    }

    @GetMapping("/{nickname}/exists")
    @Operation(summary = "닉네임 중복 체크", description = "닉네임을 전달받아 중복된 닉네임이 있는지 확인합니다.", responses = {
            @ApiResponse(responseCode = "200", description = "닉네임 중복 체크 성공", content = @Content(schema = @Schema(implementation = Boolean.class))),
            @ApiResponse(responseCode = "400", description = "잘못된 요청", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "서버 오류", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<Boolean> checkDuplicateNickname(@PathVariable String nickname) {
        Boolean result = accountService.checkDuplicateNickname(nickname);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/login")
    @Operation(summary = "로그인", description = "아이디와 패스워드를 입력해 로그인합니다.", responses = {
            @ApiResponse(responseCode = "200", description = "로그인 성공", content = @Content(schema = @Schema(implementation = AuthResponse.class))),
            @ApiResponse(responseCode = "401", description = "아이디 또는 패스워드 오류", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/refresh-token")
    @Operation(summary = "Access Token 재발급", description = "Access Token과 Refresh Token을 전달받아 Access Token을 재발급합니다.", security = @SecurityRequirement(name = "Authorization"), responses = {
            @ApiResponse(responseCode = "200", description = "Access Token 재발급 성공", content = @Content(schema = @Schema(implementation = RefreshResponse.class))),
            @ApiResponse(responseCode = "400", description = "refresh token 오류", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<RefreshResponse> refreshAccessToken(@Valid @RequestBody RefreshRequest refreshRequest, HttpServletRequest request) {
        RefreshResponse refreshResponse = accountService.refreshAccessToken(refreshRequest, request);
        return ResponseEntity.ok().body(refreshResponse);
    }

    @PatchMapping
    @Operation(summary = "회원 정보 수정", description = "수정할 회원 정보를 받아 수정합니다.", security = @SecurityRequirement(name = "Authorization"), responses = {
            @ApiResponse(responseCode = "204", description = "회원 정보 수정 성공"),
            @ApiResponse(responseCode = "400", description = "요청 값이 올바르지 않음", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<Void> updateAccount(@Valid @RequestBody AccountUpdateRequest accountUpdateRequest, @Parameter(hidden = true) @CurrentAccount Account account) {
        accountService.updateAccount(accountUpdateRequest, account);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/password")
    @Operation(summary = "비밀번호 수정", description = "현재 비밀번호와 새로운 비밀번호를 받아 변경합니다.", security = @SecurityRequirement(name = "Authorization"), responses = {
            @ApiResponse(responseCode = "204", description = "비밀번호 변경 성공"),
            @ApiResponse(responseCode = "400", description = "비밀번호가 올바르지 않음", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<Void> updatePassword(@Valid @RequestBody PasswordUpdateRequest passwordUpdateRequest, @Parameter(hidden = true) @CurrentAccount Account account) {
        accountService.updatePassword(passwordUpdateRequest, account);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    @Operation(summary = "회원 탈퇴(모든 기능 구현 완료 후 구현 예정)", description = "헤더에서 정보를 받아 회원을 탈퇴시킨다.", security = @SecurityRequirement(name = "Authorization"), responses = {
            @ApiResponse(responseCode = "204", description = "회원 탈퇴 성공"),
    })
    public ResponseEntity<Void> deleteAccount(@Parameter(hidden = true) @CurrentAccount Account account){
        accountService.deleteAccount(account);
        return ResponseEntity.noContent().build();
    }
}
