package com.epam.test.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * DAO implementation
 */

public class UserDaoImpl implements UserDao {

    String getAllUsers = "select user_id, login, password, description from app_user";
    String getUserById = "select user_id, login, password, description from app_user where user_id = :p_user_id";
    String addUser = "insert into app_user (login, password, description) values (:p_user_login, :p_user_password, :p_user_description)";
    String updateUser = "update app_user set login = :p_user_login, password = :p_user_password, description = :p_user_description where user_id = :p_user_id";
    String deleteUser = "delete from app_user where user_id = :p_user_id";
    /*
    @Value("${user.getAll}")
    private String getAllUsers;

    @Value("${user.getById}")
    private String getUserById;

    @Value("${user.add}")
    private String addUser;

    @Value("${user.update}")
    private String updateUser;

    @Value("${user.delete}")
    private String deleteUser;
    */
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public UserDaoImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<User> getAllUsers() {
        return jdbcTemplate.query(getAllUsers, new UserRowMapper());
    }

    @Override
    public User getUserById(Integer userId) {

        SqlParameterSource namedParameters = new MapSqlParameterSource("p_user_id", userId);
        return namedParameterJdbcTemplate.queryForObject(
                getUserById, namedParameters, new UserRowMapper());
    }

    @Override
    public Integer addUser(User user) {

        Map<String, Object> userParametersMap  = new HashMap<>();
        userParametersMap.put("p_user_id", user.getUserID());
        userParametersMap.put("p_user_login", user.getLogin());
        userParametersMap.put("p_user_password", user.getPassword());
        userParametersMap.put("p_user_description", user.getDescription());

        return namedParameterJdbcTemplate.update(addUser, userParametersMap);
    }

    @Override
    public void updateUser(User user) {

        Map<String, Object> userParametersMap  = new HashMap<>();
        userParametersMap.put("p_user_id", user.getUserID());
        userParametersMap.put("p_user_login", user.getLogin());
        userParametersMap.put("p_user_password", user.getPassword());
        userParametersMap.put("p_user_description", user.getDescription());
        namedParameterJdbcTemplate.update(updateUser, userParametersMap);
    }

    @Override
    public void deleteUser(Integer userId) {

        SqlParameterSource namedParameters = new MapSqlParameterSource("p_user_id", userId);
        namedParameterJdbcTemplate.update(deleteUser, namedParameters);
    }

    private class UserRowMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet resultSet, int i) throws SQLException {
            User user = new User(
                    resultSet.getInt("user_id"),
                    resultSet.getString("login"),
                    resultSet.getString("password"),
                    resultSet.getString("description"));
            return user;
        }
    }
}

