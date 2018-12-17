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
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
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
public class TestRedisTemplate {
    /**
     * 用 RedisTemplate ，去服务器上看，发现存的是 "\xac\xed\x00\x05t\x00\x06suchao" 这种样子
     */
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserService userService;

    @Test
    public void testString() {
        redisTemplate.opsForValue().set("suchao", "hello world");
        Assert.assertEquals("hello world", redisTemplate.opsForValue().get("suchao"));
    }

    @Test
    public void testObj() {
        User user = new User("suchao@mail.com", "hetao", "password", "suchao", "2018");
        /*ValueOperations<String, User> operations = redisTemplate.opsForValue();
        operations.set("com.suchao", user);
        User u = operations.get("com.suchao");
        System.out.println(u);*/
    }

    @Test
    public void testObj2() throws IOException {
        User user = new User("suchao@mail.com", "hetao", "password", "suchao", "2018");
        String writeValueAsString = objectMapper.writeValueAsString(user);
        redisTemplate.opsForValue().set("userObject", writeValueAsString);
        String userObject = redisTemplate.opsForValue().get("userObject");
        User user1 = objectMapper.readValue(userObject, User.class);
        System.out.println(user1);
    }

    @Test
    public void testExpire() throws JsonProcessingException, InterruptedException {
        User user = new User("suchao@mail.com", "hetao", "password", "suchao", "2018");
        String userString = objectMapper.writeValueAsString(user);
        redisTemplate.opsForValue().set("expireValue", userString, 100, TimeUnit.MILLISECONDS);
        Thread.sleep(1000);
        Boolean exists = redisTemplate.hasKey("expireValue");
        if (exists != null && exists) {
            System.out.println("exists is true");
        } else {
            System.out.println("exists is false");
        }
    }

    @Test
    public void testDelete() throws JsonProcessingException {
        User user = new User("suchao@mail.com", "hetao", "password", "suchao", "2018");
        String userString = objectMapper.writeValueAsString(user);
        redisTemplate.opsForValue().set("deleteValue", userString);
        Boolean exists = redisTemplate.hasKey("deleteValue");
        if (exists != null && exists) {
            System.out.println("before delete deleteValue is true");
        } else {
            System.out.println("before delete deleteValue is false");
        }

        Boolean delete = redisTemplate.delete("deleteValue");
        if (delete != null && delete) {
            System.out.println("after delete deleteValue is deleted");
        } else {
            System.out.println("after delete deleteValue is not deleted");
        }
    }
}
