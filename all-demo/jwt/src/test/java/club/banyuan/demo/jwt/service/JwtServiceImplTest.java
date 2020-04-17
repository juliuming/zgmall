package club.banyuan.demo.jwt.service;

import club.banyuan.demo.jwt.service.impl.JwtServiceImpl;
import io.jsonwebtoken.Claims;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class JwtServiceImplTest {
    private JwtServiceImpl jwtService = new JwtServiceImpl();
    @Before
    public void initField() {
        ReflectionTestUtils.setField(jwtService,"SECRETKEY","GuessWhatTheCodeIs");
        ReflectionTestUtils.setField(jwtService,"EXPIRESEC",3);
    }

    @Test
    public void generate() {
        String subject = "admin";
        String token = jwtService.generate(subject);
        assertEquals(subject, jwtService.getClaims(token).getSubject());
    }

    @Test
    public void testExpired() throws InterruptedException {
        String subject = "Durant";
        String token = jwtService.generate(subject);
        Thread.sleep(3000);
        assertTrue(jwtService.isExpired(token));
    }

    @Test
    public void refreshExpiredDateTest() throws InterruptedException {
        String subject = "Durant";
        String token = jwtService.generate(subject);
        Thread.sleep(1000);
        token = jwtService.refreshExpire(token);
        Thread.sleep(2000);
        assertFalse(jwtService.isExpired(token));
    }

    @Test
    public void generateTokenFromMapTest() {
        Map<String,Object> claims = new HashMap<>();
        claims.put(Claims.SUBJECT,"Durant");
        claims.put("Role","BasketBall player");
        String subject="Durant";
        String generateTokenFromSubect = jwtService.generate(subject);
        String generateTokenFromMap = jwtService.generate(claims);
        assertEquals(jwtService.getClaims(generateTokenFromSubect).getSubject(), jwtService.getClaims(generateTokenFromMap).getSubject());
    }
}
