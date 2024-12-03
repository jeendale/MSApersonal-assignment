package com.sparta.msa_exam.auth.controller;

import com.sparta.msa_exam.auth.dto.AuthRequestDto;
import com.sparta.msa_exam.auth.dto.SignInResDto;
import com.sparta.msa_exam.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    @Value("${server.port}")
    private String serverPort;

    private final AuthService authService;

    @PostMapping("/signIn")
    public ResponseEntity<AuthRequestDto> signIn(@RequestBody SignInResDto signInResDto){
        AuthRequestDto authRequestDto=authService.signIn(signInResDto);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Server-Port", serverPort);

        return ResponseEntity.ok()
                .headers(headers)
                .body(authRequestDto);
    }

    @PostMapping("/signUp")
    public ResponseEntity<String> signUp(@RequestBody SignInResDto signInResDto ){
        authService.signUp(signInResDto);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Server-Port", serverPort);

        return ResponseEntity.ok()
                .headers(headers)
                .body("회원가입 성공");
    }
}
