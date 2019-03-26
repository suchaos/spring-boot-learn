package com.suchaos.mapper;

import com.suchaos.model.User;
import com.suchaos.param.UserParam;

import java.util.List;

/**
 * UserMapper
 *
 * @author suchao
 * @date 2018/11/26
 */
public interface UserMapper {
    List<User> getAll();

    List<User> getList(UserParam userParam);

    int getCount(UserParam userParam);

    User getOne(Long id);

    void insert(User user);

    int update(User user);

    int delete(Long id);
}
