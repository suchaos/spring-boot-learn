package com.suchaos.redis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.suchaos.model.User;
import com.suchaos.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * TestRedisTemplate
 *
 * @author suchao
 * @date 2018/12/17
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRedisTemplate2 {

    @Autowired
    private UserService userService;

    @Test
    public void getUserById() {
        long id = 1;
        User user1 = userService.getUserById(id);
        System.out.println(user1);

        id = 2L;
        User user2 = userService.getUserById(id);
        System.out.println(user2);

        id = 1L;
        User user3 = userService.getUserById(id);
        System.out.println(user3);
    }
}
