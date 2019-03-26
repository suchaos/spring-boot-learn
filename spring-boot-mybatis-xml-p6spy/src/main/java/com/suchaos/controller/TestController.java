package com.suchaos.controller;

import com.suchaos.mapper.UserMapper;
import com.suchaos.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * TODO description class
 *
 * @author suchao
 * @date 2019/3/26
 */
@Controller
@RequestMapping("/test")
public class TestController {
    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/{id}")
    @ResponseBody
    public User getUserById(@PathVariable("id") String id) {
        return userMapper.getOne(Long.valueOf(id));
    }
}
