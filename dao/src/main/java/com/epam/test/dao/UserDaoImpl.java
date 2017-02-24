package com.epam.test.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/*
 * DAO implementation
 */
public class UserDaoImpl implements UserDao {

    private static final Logger LOGGER = LogManager.getLogger();

    static final String USER_ID = "user_id";
    static final String LOGIN = "login";
    static final String PASSWORD = "password";
    static final String DESCRIPTION = "description";

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Value("${user.getAll}")
    private String getAllUsersQuery;

    @Value("${user.getById}")
    private String getUserByIdQuery;

    @Value("${user.getByLogin}")
    private String getUserByLoginQuery;

    @Value("${user.add}")
    private String addUserQuery;

    @Value("${user.update}")
    private String updateUserQuery;

    @Value("${user.delete}")
    private String deleteUserQuery;

    public UserDaoImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<User> getAllUsers() throws DataAccessException {
        LOGGER.debug("getAllUsers()");
        return jdbcTemplate.query(getAllUsersQuery, new UserRowMapper());
    }

    @Override
    public User getUserById(Integer userId) throws DataAccessException {
        LOGGER.debug(" getUserById({})", userId);
        SqlParameterSource namedParameters = new MapSqlParameterSource("p_user_id", userId);
        return namedParameterJdbcTemplate.queryForObject(
                getUserByIdQuery, namedParameters, new UserRowMapper());
    }

    @Override
    public User getUserByLogin(String userLogin) throws DataAccessException {
        LOGGER.debug(" getUserByLogin({})", userLogin);
        SqlParameterSource namedParameters = new MapSqlParameterSource("p_user_login", userLogin);
        return namedParameterJdbcTemplate.queryForObject(
                getUserByLoginQuery, namedParameters, new UserRowMapper());
    }

    @Override
    public int addUser(User user) throws DataAccessException {
        LOGGER.debug("addUser(user): login = {}", user.getLogin());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("p_user_id", user.getUserID());
        parameterSource.addValue("p_user_login", user.getLogin());
        parameterSource.addValue("p_user_password", user.getPassword());
        parameterSource.addValue("p_user_description", user.getDescription());
        namedParameterJdbcTemplate.update(addUserQuery, parameterSource, keyHolder);
        return keyHolder.getKey().intValue();
    }

    @Override
    public int updateUser(User user) throws DataAccessException {
        LOGGER.debug("updateUser(user): login = {}", user);
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("p_user_id", user.getUserID());
        parameterSource.addValue("p_user_login", user.getLogin());
        parameterSource.addValue("p_user_password", user.getPassword());
        parameterSource.addValue("p_user_description", user.getDescription());
        return namedParameterJdbcTemplate.update(updateUserQuery, parameterSource);
    }

    @Override
    public int deleteUser(Integer userId) throws DataAccessException {
        LOGGER.debug("deleteUser(user): id = {}", userId);
        SqlParameterSource parameterSource = new MapSqlParameterSource("p_user_id", userId);
        return namedParameterJdbcTemplate.update(deleteUserQuery, parameterSource);
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

