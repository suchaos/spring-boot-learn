package com.suchaos.mapper;

import com.suchaos.enums.UserSexEnum;
import com.suchaos.model.User;
import com.suchaos.param.UserParam;
import com.suchaos.result.Page;
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

    @Test
    public void testPage() {
        UserParam userParam = new UserParam();
//		userParam.setUserSex("WOMAN");
        userParam.setCurrentPage(0);//0 是第一页，1 是第二页 依次类推
        List<User> users = userMapper.getList(userParam);
        long count = userMapper.getCount(userParam);
        Page<User> page = new Page<User>(userParam, count, users);
        System.out.println("page == " + page);
    }
}