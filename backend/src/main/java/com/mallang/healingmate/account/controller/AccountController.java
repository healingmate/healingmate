package com.mallang.healingmate.account.controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mallang.healingmate.account.domain.Account;
import com.mallang.healingmate.account.dto.request.SignupRequest;
import com.mallang.healingmate.account.service.AccountService;
import com.mallang.healingmate.common.security.CurrentAccount;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Tag(name = "Account",description = "사용자 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<String> signup(@Valid @RequestBody SignupRequest signupRequest){
        accountService.createAcount(signupRequest);
        return new ResponseEntity<String>("Created", HttpStatus.CREATED);
    }

    @GetMapping("/{nickname}/exists")
    @Operation(summary = "닉네임 중복 체크", security = @SecurityRequirement(name = "Authorization"))
    public ResponseEntity<Boolean> checkDuplicateNickname(@PathVariable String nickname){
        Boolean result = accountService.checkDuplicateNickname(nickname);
        return ResponseEntity.ok().body(result);
    }
}
