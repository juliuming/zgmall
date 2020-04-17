package club.banyuan.demo.jwt.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Map;

@Repository
public interface JwtService {
    String generate(String subject);

    String generate(Claims claims);

    String generate(Map<String, Object> claims);

    Date generateExipreTime(int expireSec);

    Jwt parse(String toke);

    Claims getClaims(String token);

    Boolean isExpired(String token);

    //To verify if the token is valid
    Boolean isTokenValid(String token);

    String refreshExpire(String token);
}
