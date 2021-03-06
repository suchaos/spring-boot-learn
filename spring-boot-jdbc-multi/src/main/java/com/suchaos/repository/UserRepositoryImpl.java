package com.suchaos.repository;

import com.suchaos.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * UserRepositoryImpl
 *
 * @author suchao
 * @date 2018/11/23
 */
@Repository
@Transactional(value = "primaryTxManager")
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    @Qualifier("primaryJdbcTemplate")
    private JdbcTemplate primaryJdbcTemplate;

    @Autowired
    @Qualifier("secondaryJdbcTemplate")
    private JdbcTemplate secondaryJdbcTemplate;

    @Override
    public int save1(User user, JdbcTemplate jdbcTemplate) {
        if (jdbcTemplate == null) {
            jdbcTemplate = primaryJdbcTemplate;
        }
        String saveSql = "INSERT INTO users(name, password, age) values(?, ?, ?)";
        int result = jdbcTemplate.update(saveSql, user.getName(), user.getPassword(), user.getAge());
        System.out.println(1 / 0);
        return result;
    }

    @Override
    @Transactional(value = "secondaryTxManager")
    public int save2(User user, JdbcTemplate jdbcTemplate) {
        if (jdbcTemplate == null) {
            jdbcTemplate = secondaryJdbcTemplate;
        }
        String saveSql = "INSERT INTO users(name, password, age) values(?, ?, ?)";
        int result = jdbcTemplate.update(saveSql, user.getName(), user.getPassword(), user.getAge());
        System.out.println(1 / 0);
        return result;
    }

    @Override
    public int update(User user, JdbcTemplate jdbcTemplate) {
        if (jdbcTemplate == null) {
            jdbcTemplate = primaryJdbcTemplate;
        }
        String updateSql = "UPDATE users SET name = ? , password = ? , age = ? WHERE id=?";
        return jdbcTemplate.update("UPDATE users SET name = ? , password = ? , age = ? WHERE id=?", user.getName(), user.getPassword(), user.getAge(), user.getId());
    }

    @Override
    public int delete(long id, JdbcTemplate jdbcTemplate) {
        if (jdbcTemplate == null) {
            jdbcTemplate = primaryJdbcTemplate;
        }
        String deleteSql = "DELETE FROM users where id = ? ";
        return jdbcTemplate.update(deleteSql, id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findALL(JdbcTemplate jdbcTemplate) {
        if (jdbcTemplate == null) {
            jdbcTemplate = primaryJdbcTemplate;
        }
        String findAllSql = "SELECT * FROM users";
        return jdbcTemplate.query(findAllSql, new UserRowMapper());
    }

    @Override
    @Transactional(readOnly = true)
    public User findById(long id, JdbcTemplate jdbcTemplate) {
        if (jdbcTemplate == null) {
            jdbcTemplate = primaryJdbcTemplate;
        }
        String findSql = "SELECT * FROM users WHERE id=?";
        return jdbcTemplate.queryForObject(findSql, new Object[]{id}, new BeanPropertyRowMapper<User>(User.class));
    }

    class UserRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int i) throws SQLException {
            User user = new User();
            user.setId(rs.getLong("id"));
            user.setName(rs.getString("name"));
            user.setPassword(rs.getString("password"));
            user.setAge(rs.getInt("age"));
            return user;
        }
    }
}
