package com.suchaos.repository;

import com.suchaos.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.endsWith;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.startsWith;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Before
    public void setUp() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd:HH-mm-ss");
        LocalDateTime localDateTime = LocalDateTime.now();
        String regTime = dateTimeFormatter.format(localDateTime);

        for (int i = 1; i <= 10; i++) {
            userRepository.save(new User(String.valueOf(i), "password" + i, i + "@gmail.com", i + "nickname", regTime));
        }
    }

    @Test
    public void test() {
        Assert.assertEquals(10, userRepository.findAll().size());
        //userRepository.delete(userRepository.findByUserName("aa"));
    }

    @Test
    public void findByUserName() {
        Assert.assertEquals("2nickname", userRepository.findByUserName("2").getNickName());
    }

    @Test
    public void findByUserNameOrEmail() {
        Assert.assertEquals("1nickname", userRepository.findByUserNameOrEmail("1", null).getNickName());
    }

    @Test
    public void findAllPagealbe() {
        int page = 2, size = 2;
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<User> users = userRepository.findAll(pageable);
        printPage(users);
    }

    @Test
    public void findByExample() {
        int page = 2, size = 2;
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page, size, sort);

        User user = new User();
        user.setPassWord("password1");
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("passWord", startsWith().ignoreCase());
        Example<User> example = Example.of(user, matcher);
        Page<User> userPage = userRepository.findAll(example, pageable);
        printPage(userPage);
    }

    private static void printPage(Page<User> userPage) {
        System.out.println("total pages: " + userPage.getTotalPages());
        System.out.println("total elements: " + userPage.getTotalElements());
        userPage.forEach(System.out::println);
    }
}