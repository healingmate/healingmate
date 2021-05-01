package com.mallang.healingmate.account.controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mallang.healingmate.account.domain.Account;
import com.mallang.healingmate.account.dto.request.SignupRequest;
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

import javax.validation.Valid;

/**
 * com.mallang.healingmate.account.controller
 * AccountController.java
 * @date    2021-04-28 오전 10:00
 * @author  이아영
 *
 * @변경이력
 **/

@Tag(name = "Account",description = "사용자 API")
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
    public ResponseEntity<String> signup(@Valid @RequestBody SignupRequest signupRequest){
        accountService.createAcount(signupRequest);
        return new ResponseEntity<String>("Created", HttpStatus.CREATED);
    }

    @GetMapping("/{nickname}/exists")
    @Operation(summary = "닉네임 중복 체크", description = "닉네임을 전달받아 중복된 닉네임이 있는지 확인합니다.", responses = {
            @ApiResponse(responseCode = "200", description = "닉네임 중복 체크 성공", content = @Content(schema = @Schema(implementation = Boolean.class))),
            @ApiResponse(responseCode = "400", description = "잘못된 요청", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "서버 오류", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<Boolean> checkDuplicateNickname(@PathVariable String nickname){
        Boolean result = accountService.checkDuplicateNickname(nickname);
        return ResponseEntity.ok().body(result);
    }
}
