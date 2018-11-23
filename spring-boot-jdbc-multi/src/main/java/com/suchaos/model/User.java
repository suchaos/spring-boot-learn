package com.suchaos.model;

import lombok.Data;

/**
 * User
 *
 * @author suchao
 * @date 2018/11/23
 */
@Data
public class User {
    private Long id;
    private String name;
    private String password;
    private int age;

    public User() {

    }

    public User(String name, String password, int age) {
        this.name = name;
        this.password = password;
        this.age = age;
    }
}
