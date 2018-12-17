package com.suchaos.service;

import com.suchaos.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * UserService
 *
 * @author suchao
 * @date 2018/12/17
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Override
    @Cacheable(cacheNames="realTimeCache", key = "'num_' + #id")
    public User getUserById(long id) {
        log.info("没有走缓存");
        String userName = "suchao_" + id;
        User user = new User("suchao@mail.com", "hetao", "password", userName, "2018");
        user.setId(id);
        return user;
    }

    @Override
    public void deleteUserById(long id) {

    }
}
