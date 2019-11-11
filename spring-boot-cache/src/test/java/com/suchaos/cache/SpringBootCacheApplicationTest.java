package com.suchaos.cache;

import com.suchaos.cache.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class SpringBootCacheApplicationTest {

    @Autowired
    // k-v 都是对象
    private RedisTemplate redisTemplate;

    @Autowired
    // k-v 都是字符串
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private EmployeeService employeeService;

    /*
        Redis 常见的五大数据类型：
            String, List, Set, Hash, Zset（有序集合）
            StringRedisTemplate.opsForxxx 表示分别操作不同的类型
     */
    @Test
    public void redisWrite() {
        stringRedisTemplate.opsForValue().append("msg", "spring boot data redis");
    }

    @Test
    public void redisRead() {
        String msg = stringRedisTemplate.opsForValue().get("msg");
        Assert.assertEquals("spring boot data redis", msg);
    }

    @Test
    public void redisList() {
        stringRedisTemplate.opsForList().leftPush("mylist1", "1");
        stringRedisTemplate.opsForList().leftPush("mylist1", "2");
        stringRedisTemplate.opsForList().leftPush("mylist1", "3");
    }

    @Test
    public void saveObject() {
        // 默认保存对象，使用 jdk 序列化机制，序列化后的数据保存到 redis 中
        redisTemplate.opsForValue().set("emp-01", employeeService.getEmployeeById(1));
        /*
            将数据以 json 的格式保存
                1.自己将对象转成 json
                2.修改默认的序列化机制
         */
    }

    @Test
    public void readObject() {
        Object o = redisTemplate.opsForValue().get("emp-01");
        log.info("readObject: {}", o);
    }

}