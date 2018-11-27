package com.suchaos.mapper;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.suchaos.enums.UserSexEnum;
import com.suchaos.model.User;
import com.suchaos.param.UserParam;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void getAll() {
        PageHelper.startPage(1, 10);
        List<User> users = userMapper.getAll();
        users.forEach(System.out::println);
    }

    @Test
    public void getList() {
        PageHelper.startPage(1, 10);
        UserParam userParam = new UserParam();
        userParam.setUserSex("MAN");
        List<User> users = userMapper.getList(userParam);

        PageInfo<User> pageInfo = new PageInfo<>(users);

        users.forEach(System.out::println);
        System.out.println(pageInfo);
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