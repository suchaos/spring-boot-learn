package com.suchaos.repository;

import com.suchaos.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    @Qualifier("primaryJdbcTemplate")
    private JdbcTemplate primaryJdbcTemplate;

    @Autowired
    @Qualifier("secondaryJdbcTemplate")
    private JdbcTemplate secondaryJdbcTemplate;

    @Test
    public void save() {
        User user = new User("smile", "123456", 30);
        userRepository.save1(user, primaryJdbcTemplate);
    }

    @Test
    public void save2() {
        User user = new User("smile", "123456", 30);
        userRepository.save2(user, secondaryJdbcTemplate);
    }

    @Test
    public void save3() {
        User user = new User("smile", "123456", 30);
        userRepository.save2(user, null);
    }

    @Test
    public void update() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void findALL() {
    }

    @Test
    public void findById() {
    }
}