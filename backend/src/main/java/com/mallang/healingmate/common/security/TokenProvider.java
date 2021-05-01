package com.mallang.healingmate.common.security;

import com.mallang.healingmate.account.domain.UserAccount;
import com.mallang.healingmate.common.config.AppProperties;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * com.mallang.healingmate.common.security
 * TokenProvider.java
 * @date    2021-05-01 오후 8:40
 * @author  이아영
 *
 * @변경이력
 **/

@Slf4j
@RequiredArgsConstructor
@Service
public class TokenProvider {

    private final AppProperties appProperties;

    public String createToken(Authentication authentication){
        UserAccount userAccount = (UserAccount) authentication.getPrincipal();

        Date now = new Date();
        Date expiryDate = new Date((now.getTime()+appProperties.getAuth().getTokenExpirationMsec()));
        log.info("expiryDate: "+expiryDate.toString());

        return Jwts.builder()
                .setSubject(userAccount.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, appProperties.getAuth().getTokenSecret())
                .compact();
    }

    public String getUserIdFromToken(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(appProperties.getAuth().getTokenSecret())
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public boolean validateToken(String authToken){
        try{
            Jwts.parser().setSigningKey(appProperties.getAuth().getTokenSecret()).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e){
            log.error("Invalid JWT signature");
        } catch (MalformedJwtException e){
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException e){
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException e){
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException e){
            log.error("JWT claims string is empty");
        }
        return  false;
    }
}
