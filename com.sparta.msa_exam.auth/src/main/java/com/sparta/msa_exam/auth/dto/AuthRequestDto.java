package com.sparta.msa_exam.auth.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
public class AuthRequestDto {
    private String token;

    public AuthRequestDto(String token) {
        this.token = token;
    }
}
