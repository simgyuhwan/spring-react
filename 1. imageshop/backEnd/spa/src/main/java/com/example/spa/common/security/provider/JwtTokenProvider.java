package com.example.spa.common.security.provider;

import com.example.spa.common.security.domain.CustomUser;
import com.example.spa.common.security.jwt.constants.SecurityConstants;
import com.example.spa.domain.Member;
import com.example.spa.prop.ShopProperties;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtTokenProvider {
    private final ShopProperties shopProperties;

    // 토큰을 통해서 userNo 를 가져온다.
    public long getUserNo(String header) throws Exception{
        String token = header.substring(7);

        byte[] signingKey = getSigningKey();

        Jws<Claims> parsedToken = Jwts.parserBuilder()
                .setSigningKey(signingKey)
                .build().parseClaimsJws(token);

        Claims claims = parsedToken.getBody();
        long userNo  = Long.parseLong((String)claims.get("uno"));

        return userNo;
    }
    public String createToken(long userNo, String userId, List<String> roles){
        byte[] signingKey = getSigningKey();

        String token = Jwts.builder()
                .signWith(Keys.hmacShaKeyFor(signingKey), SignatureAlgorithm.HS512)
                .setHeaderParam("typ", SecurityConstants.TOKEN_TYPE)
                .setExpiration(new Date(System.currentTimeMillis() + 864000000))
                .claim("uno",""+userNo)
                .claim("uid", userId)
                .claim("rol", roles)
                .compact();
        return token;
    }

    public UsernamePasswordAuthenticationToken getAuthentication(String tokenHeader){
        if(isNotEmpty(tokenHeader)){
            try{
                // Key 값을 가져옵니다.
                byte[] signingKey = getSigningKey();

                // parserBuilder 를 생성한 뒤, 토큰 값을 넣어서 Jws 를 생성합니다.
                // Jws 를 생성하면 메소드를 이용해서 토큰 내부의 값을 parse 해서 가져올 수 있습니다.
                Jws<Claims> parsedToken = Jwts.parserBuilder()
                        .setSigningKey(signingKey)
                        .build().parseClaimsJws(tokenHeader.replace("Bearer ", ""));

                // 토큰 내부에 있는 정보를 추출합니다.
                // Jwt 값에서 Body 부분만 가져옵니다.
                Claims claims = parsedToken.getBody();

                // 처음에 넣어뒀던 userNo, userId 값을 가져옵니다.
                String userNo = (String)claims.get("uno");
                String userId = (String)claims.get("uid");

                // 권한도 가져옵니다.
                List<SimpleGrantedAuthority> authorities = ((List<?>) claims.get("rol")).stream()
                        .map(auth -> new SimpleGrantedAuthority((String) auth))
                        .collect(Collectors.toList());

                // userId 값이 있는지 확인합니다.
                if(isNotEmpty(userId)){
                    Member member = new Member();
                    member.setUserNo(Long.parseLong(userNo));
                    member.setUserId(userId);
                    member.setUserPw("");

                    // 토큰에 있던 값들로 UserDetail 만듭니다.(비밀번호 값을 안넣는 이유는 이 메소드는 토큰이 존재하는 경우에 사용되기 때문이다.
                    // 토큰이 잘못되면 아래의 catch 와 같이 다 필터를 해준다.
                    UserDetails userDetail = new CustomUser(member, authorities);

                    // 바로 Authentication 으로 반환해도 되지만 구현 클래스인 UsernamePasswordAuthenticationToken 로 반환해준다.
                    return new UsernamePasswordAuthenticationToken(userDetail, null, authorities);
                }
            }catch (ExpiredJwtException exception){
                log.warn("Request to parse expired JWT : {} failed : {} ", tokenHeader, exception.getMessage());
            }catch (UnsupportedJwtException exception){
                log.warn("Request to parse unsupported JWT : {} failed : {}", tokenHeader, exception.getMessage());
            }catch (MalformedJwtException exception){
                log.warn("Request to parse invalid JWT : {} failed : {} ", tokenHeader, exception.getMessage());
            }catch (IllegalStateException exception){
                log.warn("Request to parse empty or null JWT : {} failed : {}", tokenHeader, exception.getMessage());
            }
        }
        return null;
    }

    private byte[] getSigningKey(){
        return shopProperties.getSecretKey().getBytes();
    }

    private boolean isNotEmpty(final CharSequence cs){
        return !isEmpty(cs);
    }

    private boolean isEmpty(final CharSequence cs){
        return cs == null || cs.length() ==0;
    }

    public boolean validateToken(String jwtToken){
        try{
            Jws<Claims> claims = Jwts.parserBuilder()
                    .setSigningKey(shopProperties.getSecretKey()).build().parseClaimsJws(jwtToken);
            return claims.getBody().getExpiration().after(new Date());
        }catch(ExpiredJwtException exception){
            log.error("Token Expired");
            return false;
        }catch (JwtException exception){
            log.error("Token Tampered");
            return false;
        }catch (NullPointerException exception){
            log.error("Token is null");
            return false;
        }catch(Exception e){
            return false;
        }
    }
}
