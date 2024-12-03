package com.sparta.msa_exam.auth.service;

import com.sparta.msa_exam.auth.dto.AuthRequestDto;
import com.sparta.msa_exam.auth.dto.SignInResDto;
import com.sparta.msa_exam.auth.entity.User;
import com.sparta.msa_exam.auth.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class AuthService {
    @Value("${spring.application.name}")
    private String issuer;

    @Value("${service.jwt.access-expiration}")
    private Long accessExpiration;

    private final SecretKey secretKey;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(@Value("${service.jwt.secret-key}") String secretKey,
                       UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(secretKey));
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String createAccessToken(String username) {
        return Jwts.builder()
                .claim("username", username)
                .issuer(issuer)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + accessExpiration))
                .signWith(secretKey, io.jsonwebtoken.SignatureAlgorithm.HS512)
                .compact();
    }



    public AuthRequestDto signIn(SignInResDto signInResDto) {
        User user= userRepository.findByUsername(signInResDto.getUsername()).orElseThrow(()
        -> new IllegalArgumentException("존재하지 않는 아이디 입니다."));

        if (!passwordEncoder.matches(signInResDto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("패스워드가 일치하지 않습니다.");
        }

        return AuthRequestDto.builder()
                .token(createAccessToken(user.getUsername()))
                .build();
    }

    public void signUp(SignInResDto signInResDto) {
        User user= User.builder()
                .username(signInResDto.getUsername())
                .password(passwordEncoder.encode(signInResDto.getPassword()))
                .build();

        userRepository.save(user);
    }
}
