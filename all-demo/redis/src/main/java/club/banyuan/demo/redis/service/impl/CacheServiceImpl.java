package club.banyuan.demo.redis.service.impl;

import club.banyuan.demo.redis.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class CacheServiceImpl implements CacheService {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T get(String key) {
        try {
            return (T) redisTemplate.opsForValue().get(key);
        }catch (ClassCastException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Boolean expire(String key, long sec) {
        return redisTemplate.expire(key,sec, TimeUnit.SECONDS);
    }

    @Override
    public Long getExpireSec(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    @Override
    public void flushdb() {
        Set<String> keys = redisTemplate.keys("*");
        if(keys!=null){
            redisTemplate.delete(keys);
        }
    }
}
