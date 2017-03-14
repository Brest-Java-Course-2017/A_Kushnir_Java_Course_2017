package com.kushnir.dao;

import com.kushnir.model.Journalist;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Journalist DAO implementation
 */
public class JournalistDaoImpl implements JournalistDao {

    private static final Logger LOGGER = LogManager.getLogger();

    static final String ID_FIELDNAME = "id_journalist";
    static final String NAME_FIELDNAME = "name_journalist";
    static final String BIRTHDATE_FIELDNAME = "birth_date";
    static final String RATING_FIELDNAME = "rating";

    static final String COUNTARTICLES_FIELDNAME = "countArticles";

    static final String ID_PARAMETERNAME = "p_id_j";
    static final String NAME_PARAMETERNAME = "p_name";
    static final String BIRTHDAY_PARAMETERNAME = "p_birth_date";
    static final String RATING_PARAMETERNAME = "p_rating";

    static final String BIRTHDAY_START_PARAMETERNAME = "p_birthDateStart";
    static final String BIRTHDAY_END_PARAMETERNAME = "p_birthDateEnd";

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Value("${journalist.selectAll}")
    String getAllJournalistsSqlquery;

    @Value("${journalist.selectById}")
    String getJournalistByIdSqlQuery;

    @Value("${journalist.selectByName}")
    String getJournalistByNameSqlQuery;

    @Value("${journalist.insert}")
    String addJournalistSqlQuery;

    @Value("${journalist.update}")
    String updateJournalistSqlQuery;

    @Value("${journalist.delete}")
    String deleteJournalistSqlQuery;

    public JournalistDaoImpl(DataSource dataSource) {
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<Journalist> getAllJournalists(LocalDate birthDateStart, LocalDate birthDateEnd) throws DataAccessException {
        LOGGER.debug("getAllJournalists(birthDateStart: " + birthDateStart + ", birthDateEnd: " + birthDateEnd);
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue(BIRTHDAY_START_PARAMETERNAME, birthDateStart);
        parameterSource.addValue(BIRTHDAY_END_PARAMETERNAME, birthDateEnd);
        List<Journalist> journalistList = namedParameterJdbcTemplate.query(
                getAllJournalistsSqlquery
                ,parameterSource
                ,new JournalistsRowMapper());
        return journalistList;
    }

    @Override
    public Journalist.JournalistDisplayPage getDataJournalistDisplayPage(LocalDate birthDateStart, LocalDate birthDateEnd) {
        LOGGER.debug("getDataJournalistDisplayPage(birthDateStart: " + birthDateStart + ", birthDateEnd: " + birthDateEnd);
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue(BIRTHDAY_START_PARAMETERNAME, birthDateStart);
        parameterSource.addValue(BIRTHDAY_END_PARAMETERNAME, birthDateEnd);
        Journalist.JournalistDisplayPage journalistDisplayPage = namedParameterJdbcTemplate.queryForObject(
                getAllJournalistsSqlquery
                ,parameterSource
                ,new JournalistDisplayPageRowMapper());
        return journalistDisplayPage;
    }

    @Override
    public Journalist getJournalistById(Integer id) throws DataAccessException {
        LOGGER.debug("getJournalistById(id: " + id + ")");
        SqlParameterSource namedParameters = new MapSqlParameterSource(ID_PARAMETERNAME, id);
        Journalist journalist = namedParameterJdbcTemplate.queryForObject(
                getJournalistByIdSqlQuery
                    ,namedParameters
                    ,new JournalistsRowMapper());
        return journalist;
    }

    @Override
    public Journalist getJournalistByName(String name) throws DataAccessException {
        LOGGER.debug("getJournalistByName(name: " + name + ")");
        SqlParameterSource namedParameters = new MapSqlParameterSource(NAME_PARAMETERNAME, name);
        Journalist journalist = namedParameterJdbcTemplate.queryForObject(
                getJournalistByNameSqlQuery
                    ,namedParameters
                    ,new JournalistsRowMapper());
        return journalist;
    }

    @Override
    public Integer addJournalist(Journalist journalist) throws DataAccessException {
        LOGGER.debug("addJournalist(journalistName: " + journalist.getName() + ")");
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue(NAME_PARAMETERNAME, journalist.getName());
        parameterSource.addValue(BIRTHDAY_PARAMETERNAME, journalist.getBirthDate());
        parameterSource.addValue(RATING_PARAMETERNAME, journalist.getRate());
        namedParameterJdbcTemplate.update(addJournalistSqlQuery, parameterSource, keyHolder);
        return keyHolder.getKey().intValue();
    }

    @Override
    public Integer updateJournalist(Journalist journalist) throws DataAccessException {
        LOGGER.debug("updateJournalist(journalistName: " + journalist.getName() + ")");
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue(ID_PARAMETERNAME, journalist.getId());
        parameterSource.addValue(NAME_PARAMETERNAME, journalist.getName());
        parameterSource.addValue(BIRTHDAY_PARAMETERNAME, journalist.getBirthDate());
        parameterSource.addValue(RATING_PARAMETERNAME, journalist.getRate());
        return namedParameterJdbcTemplate.update(updateJournalistSqlQuery, parameterSource);
    }

    @Override
    public Integer deleteJournalist(Integer id) throws DataAccessException {
        LOGGER.debug("deleteJournalist(id: " + id + ")");
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue(ID_PARAMETERNAME, id);
        return namedParameterJdbcTemplate.update(deleteJournalistSqlQuery, parameterSource);
    }

    private class JournalistsRowMapper implements RowMapper<Journalist> {

        @Override
        public Journalist mapRow(ResultSet resultSet, int i) throws SQLException {
            Journalist journalist = new Journalist(
                    resultSet.getInt(ID_FIELDNAME)
                    ,resultSet.getString(NAME_FIELDNAME)
                    ,resultSet.getInt(RATING_FIELDNAME)
                    ,resultSet.getDate(BIRTHDATE_FIELDNAME).toLocalDate()
                    ,resultSet.getInt(COUNTARTICLES_FIELDNAME));
            return journalist;
        }
    }

    private class JournalistDisplayPageRowMapper implements RowMapper<Journalist.JournalistDisplayPage> {

        @Override
        public Journalist.JournalistDisplayPage mapRow(ResultSet resultSet, int i) throws SQLException {
            List<Journalist> journalistList = new ArrayList<>();
            Map<String, Integer> aggregativeValues = new HashMap<>();
            int count = 0;
            int sumRating = 0;

            while(resultSet.next()) {
                journalistList.add(new Journalist(
                        resultSet.getInt(ID_FIELDNAME)
                        ,resultSet.getString(NAME_FIELDNAME)
                        ,resultSet.getInt(RATING_FIELDNAME)
                        ,resultSet.getDate(BIRTHDATE_FIELDNAME).toLocalDate()
                        ,resultSet.getInt(COUNTARTICLES_FIELDNAME)));

                count ++;
                sumRating += resultSet.getInt(RATING_FIELDNAME);
            }

            aggregativeValues.put("fieldsCount", count);
            aggregativeValues.put("sumRating", sumRating);
            aggregativeValues.put("avgRating", sumRating/count);

            return new Journalist.JournalistDisplayPage(journalistList, aggregativeValues);
        }
    }
// BeanPropertySqlParameterSource
}