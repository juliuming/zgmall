package club.banyuan.demo.jwt.service.impl.impl;

import club.banyuan.demo.jwt.service.impl.JwtService;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class JwtServiceImpl implements JwtService {
    // Secret key
    @Value("${token.secretKe}")
    String SECRETKEY;

    // Expire time
    @Value("${token.expireSe}")
    int EXPIRESEC;

    /**
     * Generate the token with the given secret key and the expire time and
     * returns
     * the jwt token.
     *
     * @param subject the subject  messages (maybe username) that you want put in the playload of jwt.
     * @return jwt token signed with gievn secret key and expire time.
     */
    @Override
    public String generate(String subject) {
        return Jwts.builder()
                .setSubject(subject)
                .setExpiration(generateExipreTime(EXPIRESEC))
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, SECRETKEY)
                .compact();
    }

    /**
     * Generate the token with the given secret key and the expire time and
     * returns
     * the jwt token.
     *
     * @param claims A map like collection packaging the messages that you want put in the playload of jwt.
     * @return jwt token signed with given secret key and expire time.
     */
    @Override
    public String generate(Claims claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExipreTime(EXPIRESEC))
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, SECRETKEY)
                .compact();
    }


    @Override
    public String generate(Map<String, Object> claims){
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExipreTime(EXPIRESEC))
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256,SECRETKEY)
                .compact();
    }

    /**
     * Generate the expire time when the method is called
     * returns
     * a Date instance.
     *
     * @param expireSec the time in seconds you want the jwt lives.
     * @return Date instance of the expire time.
     */
    @Override
    public Date generateExipreTime(int expireSec) {
        long currentMiles = System.currentTimeMillis();
        long expireMiles = currentMiles + (long) (expireSec * 1000);
        return new Date(expireMiles);
    }

    @Override
    public Jws<Claims> parse(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(SECRETKEY)
                    .parseClaimsJws(token);
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException |
                IllegalArgumentException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * Parse a token and
     * returns
     * the claims.
     *
     * @param token the token string.
     * @return claims of the token.
     */
    @Override
    public Claims getClaims(String token) throws IllegalArgumentException {
        Jws<Claims> jws = parse(token);
        return jws.getBody();
    }

    //To verify if the token is expired
    @Override
    public Boolean isExpired(String token) {
        Claims claims;
        try {
            claims = getClaims(token);
        }catch (IllegalArgumentException e){
            return true;
        }
        Date date = claims.getExpiration();
        if (date == null) {
            throw new IllegalArgumentException("illegal expiration");
        }
        return (date.compareTo(new Date()) < 0);
    }

    //To verify if the token is valid
    public Boolean isTokenValid(String token) {
        try {
            return !isExpired(token);
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    //To refresh the expire time
    @Override
    public String refreshExpire(String token) {
        Claims claims = getClaims(token);
        return generate(claims);
    }
}
