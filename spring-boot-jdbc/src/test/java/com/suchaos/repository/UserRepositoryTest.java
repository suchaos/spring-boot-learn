package com.suchaos.repository;

import com.suchaos.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void save() {
        User user = new User("ming", "123456", 18);
        userRepository.save(user);
    }

    @Test
    public void update() {
        User user =new User("jun","123456",21);
        user.setId(1L);
        userRepository.update(user);
    }

    @Test
    public void delete() {
        userRepository.delete(1L);
    }

    @Test
    public void findALL() {
        List<User> users=userRepository.findALL();
        for (User user:users){
            System.out.println("user == "+user.toString());
        }
    }

    @Test
    public void findById() {
        User user=userRepository.findById(2L);
        System.out.println("user == "+user.toString());
    }
}