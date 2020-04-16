package club.banyuan.demo.redis.service.impl;

import club.banyuan.demo.redis.model.User;
import club.banyuan.demo.redis.service.UserService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

public class UserServiceImpl implements UserService {
    @Cacheable(value = "user", key = "#username")
    @Override
    public User getUser(String username) {
        return null;
    }

    @CachePut(value = "user", key = "#username")
    @Override
    public User updateUser(String username, String password) {
        return null;
    }

    @CacheEvict(value = "user", key="username")
    @Override
    public boolean deleteUser(String username, String password) {
        return false;
    }
}
