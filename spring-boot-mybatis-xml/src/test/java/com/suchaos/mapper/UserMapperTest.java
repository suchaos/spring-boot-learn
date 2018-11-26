package com.suchaos.mapper;

import com.suchaos.enums.UserSexEnum;
import com.suchaos.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void getAll() {
    }

    @Test
    public void getOne() {
    }

    @Test
    public void insert() {
        userMapper.insert(new User("aa", "a123456", UserSexEnum.MAN));
    }

    @Test
    public void update() {
    }

    @Test
    public void delete() {
    }
}