package com.suchaos.model;

import com.suchaos.enums.UserSexEnum;
import lombok.Data;

/**
 * User
 *
 * @author suchao
 * @date 2018/11/26
 */
@Data
public class User {
    private Long id;
    private String userName;
    private String passWord;
    private UserSexEnum userSex;
    private String nickName;

    public User() {
        super();
    }

    public User(String userName, String passWord, UserSexEnum userSex) {
        super();
        this.passWord = passWord;
        this.userName = userName;
        this.userSex = userSex;
    }
}
