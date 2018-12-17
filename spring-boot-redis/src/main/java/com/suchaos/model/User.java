package com.suchaos.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

/**
 * User
 *
 * @author suchao
 * @date 2018/12/17
 */
@Data
@NoArgsConstructor
public class User {
    private Long id;
    private String userName;
    private String password;
    private String email;
    private String nickname;
    private String regTime;

    public User(String email, String nickname, String password, String userName, String regTime) {
        super();
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.userName = userName;
        this.regTime = regTime;
    }
}
