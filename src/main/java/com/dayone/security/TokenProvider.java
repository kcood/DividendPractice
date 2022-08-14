package com.dayone.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TokenProvider {

    private static final String KEY_ROLES = "roles";
    private static final long TOKEN_EXPIRE_TIME = 1000*60*60; //1시간

    @Value("{spring.jwt.secret}")
    private String secretKey;

    //=====토큰 생성(발급) 메서드=======
    public String generateToken(String username, List<String> roles){
        //사용자 권한정보 저장을 위한 claims 만들기
        Claims claims = Jwts.claims().setSubject(username);
        claims.put(KEY_ROLES,roles);

        var now = new Date();
        var expiredDate = new Date(now.getTime() + TOKEN_EXPIRE_TIME);

        //claims정보와 만료시간 token에 넣어서 생성
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now) //토큰 생성시간
                .setExpiration(expiredDate) //만료시간
                .signWith(SignatureAlgorithm.ES512, this.secretKey) //사용할 암호화 알고리즘과 비밀키
                .compact();
    }


    public String getUserName(String token){
        return this.parseClaims(token).getSubject();
    }

    public boolean validateToken(String token){
        if (!StringUtils.hasText(token)) return false;

        var claims = this.parseClaims(token);
        return claims.getExpiration().before(new Date());
    }

    //=====토큰이 유효한지 확인하는 메서드=======
    private Claims parseClaims(String token) {
        try {
            return Jwts.parser().setSigningKey(this.secretKey).parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

}
