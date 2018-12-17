package com.suchaos.service;

import com.suchaos.model.User;

public interface UserService {
    User getUserById(long id);

    void deleteUserById(long id);
}
