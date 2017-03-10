package com.kushnir.dao;

import com.kushnir.model.Journalist;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
//import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

/**
 * Journalist DAO implementation
 */
public class JournalistDaoImpl implements JournalistDao {

    static final String JOURNALIST_ID_FIELDNAME = "id_journalist";
    static final String JOURNALIST_NAME_FIELDNAME = "name_journalist";
    static final String JOURNALIST_BIRTHDATE_FIELDNAME = "birth_date";
    static final String JOURNALIST_RATING_FIELDNAME = "rating";
    static final String JOURNALIST_COUNTOFARTICLES_FIELDNAME = "countArticles";

    static final String JOURNALIST_ID_PARAMETERNAME = "p_id_j";
    static final String JOURNALIST_NAME_PARAMETERNAME = "p_name";
    static final String JOURNALIST_BIRTHDAY_PARAMETERNAME = "p_birth_date";
    static final String JOURNALIST_RATING_PARAMETERNAME = "p_rating";

    //private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Value("${journalist.selectAll}")
    String getAllJournalistsSqlquery;

    @Value("${journalist.selectById}")
    String getJournalistByIdSqlQuery;

    @Value("${journalist.insert}")
    String addJournalistSqlQuery;

    @Value("${journalist.update}")
    String updateJournalistSqlQuery;

    @Value("${journalist.delete}")
    String deleteJournalistSqlQuery;

    public JournalistDaoImpl(DataSource dataSource) {
        //jdbcTemplate = new JdbcTemplate(dataSource);
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<Journalist> getAllJournalists(LocalDate birthDateStart, LocalDate birthDateEnd) throws DataAccessException {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("p_birthDateStart", birthDateStart);
        parameterSource.addValue("p_birthDateEnd", birthDateEnd);
        return namedParameterJdbcTemplate.query(getAllJournalistsSqlquery, parameterSource ,new JournalistsRowMapper());
    }

    @Override
    public Journalist getJournalistById(Integer id) throws DataAccessException {
        SqlParameterSource namedParameters = new MapSqlParameterSource(JOURNALIST_ID_PARAMETERNAME, id);
        return namedParameterJdbcTemplate.queryForObject(getJournalistByIdSqlQuery, namedParameters, new JournalistsRowMapper());
    }

    @Override
    public Integer addJournalist(Journalist journalist) throws DataAccessException {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue(JOURNALIST_NAME_PARAMETERNAME, journalist.getName());
        parameterSource.addValue(JOURNALIST_BIRTHDAY_PARAMETERNAME, journalist.getBirthDate());
        parameterSource.addValue(JOURNALIST_RATING_PARAMETERNAME, journalist.getRate());
        namedParameterJdbcTemplate.update(addJournalistSqlQuery, parameterSource, keyHolder);
        return keyHolder.getKey().intValue();
    }

    @Override
    public Integer updateJournalist(Journalist journalist) throws DataAccessException {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue(JOURNALIST_ID_PARAMETERNAME, journalist.getId());
        parameterSource.addValue(JOURNALIST_NAME_PARAMETERNAME, journalist.getName());
        parameterSource.addValue(JOURNALIST_BIRTHDAY_PARAMETERNAME, journalist.getBirthDate());
        parameterSource.addValue(JOURNALIST_RATING_PARAMETERNAME, journalist.getRate());
        return namedParameterJdbcTemplate.update(updateJournalistSqlQuery, parameterSource);
    }

    @Override
    public Integer deleteJournalist(Integer id) throws DataAccessException {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue(JOURNALIST_ID_PARAMETERNAME, id);
        return namedParameterJdbcTemplate.update(deleteJournalistSqlQuery, parameterSource);
    }

    private class JournalistsRowMapper implements RowMapper<Journalist> {

        @Override
        public Journalist mapRow(ResultSet resultSet, int i) throws SQLException {
            Journalist journalist = new Journalist(
                    resultSet.getInt(JOURNALIST_ID_FIELDNAME),
                    resultSet.getString(JOURNALIST_NAME_FIELDNAME),
                    resultSet.getInt(JOURNALIST_RATING_FIELDNAME),
                    resultSet.getDate(JOURNALIST_BIRTHDATE_FIELDNAME).toLocalDate(),
                    resultSet.getInt(JOURNALIST_COUNTOFARTICLES_FIELDNAME));
            return journalist;
        }
    }

}
