package com.kushnir.dao;

import com.kushnir.model.Journalist;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

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

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Value("${journalist.selectAll}")
    String getAllJournalistsSqlquery;

    public JournalistDaoImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
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
        //TODO implement getJournalistById() method
        return null;
    }

    @Override
    public Integer addJournalist(Journalist journalist) throws DataAccessException {
        //TODO implement addJournalist() method
        return null;
    }

    @Override
    public Integer updateJournalist(Journalist journalist) throws DataAccessException {
        //TODO implement updateJournalist() method
        return null;
    }

    @Override
    public Integer deleteJournalist(Integer id) throws DataAccessException {
        //TODO implement deleteJournalist() method
        return null;
    }

    private class JournalistsRowMapper implements RowMapper<Journalist> {

        @Override
        public Journalist mapRow(ResultSet resultSet, int i) throws SQLException {
            Journalist journalist = new Journalist(
                    resultSet.getInt(JOURNALIST_ID_FIELDNAME),
                    resultSet.getString(JOURNALIST_NAME_FIELDNAME),
                    resultSet.getInt(JOURNALIST_RATING_FIELDNAME),
                    resultSet.getDate(JOURNALIST_BIRTHDATE_FIELDNAME).toLocalDate());
            return journalist;
        }
    }

}
