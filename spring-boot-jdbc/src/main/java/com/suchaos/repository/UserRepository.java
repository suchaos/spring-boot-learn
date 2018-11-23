package com.suchaos.repository;

import com.suchaos.model.User;

import java.util.List;

/**
 * UserRepository
 *
 * @author suchao
 * @date 2018/11/23
 */
public interface UserRepository {
    int save(User user);

    int update(User user);

    int delete(long id);

    List<User> findALL();

    User findById(long id);
}
