package com.suchaos.repository;

import com.suchaos.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

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
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(User user) {
        String saveSql = "INSERT INTO users(name, password, age) values(?, ?, ?)";
        return jdbcTemplate.update(saveSql, user.getName(), user.getPassword(), user.getAge());
    }

    @Override
    public int update(User user) {
        String updateSql = "UPDATE users SET name = ? , password = ? , age = ? WHERE id=?";
        return jdbcTemplate.update("UPDATE users SET name = ? , password = ? , age = ? WHERE id=?", user.getName(), user.getPassword(), user.getAge(), user.getId());
    }

    @Override
    public int delete(long id) {
        String deleteSql = "DELETE FROM users where id = ? ";
        return jdbcTemplate.update(deleteSql, id);
    }

    @Override
    public List<User> findALL() {
        String findAllSql = "SELECT * FROM users";
        return jdbcTemplate.query(findAllSql, new UserRowMapper());
    }

    @Override
    public User findById(long id) {
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
