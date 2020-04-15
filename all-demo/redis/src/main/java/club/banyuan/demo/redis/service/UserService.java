package club.banyuan.demo.redis.service;

import club.banyuan.demo.redis.model.User;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

public interface UserService {


    @Cacheable(value = "user", key = "#username")
    User getUser(String username);

    @CachePut(value = "user", key = "#username")
    User updateUser(String username, String password);

    @CacheEvict(value = "user", key = "#username")
    boolean deleteUser(String username, String password);
}
