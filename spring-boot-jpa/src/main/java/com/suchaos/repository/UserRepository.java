package com.suchaos.repository;

import com.suchaos.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * UserRepository
 *
 * @author suchao
 * @date 2018/12/3
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String userName);

    User findByUserNameOrEmail(String userName, String email);
}
