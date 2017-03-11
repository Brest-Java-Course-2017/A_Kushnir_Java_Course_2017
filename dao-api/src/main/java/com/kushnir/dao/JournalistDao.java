package com.kushnir.dao;

import com.kushnir.model.Journalist;
import org.springframework.dao.DataAccessException;

import java.time.LocalDate;
import java.util.List;

/**
 * Journalist DAO interface
 */
public interface JournalistDao {

    /**
     * Get all journalists from database.
     *
     * @param birthDateStart
     * @param birthDateEnd
     * @return all journalists from DB
     * @returnType List<Journalist>
     * @throws DataAccessException
     */
    List<Journalist> getAllJournalists(LocalDate birthDateStart, LocalDate birthDateEnd) throws DataAccessException;

    /**
     * Get journalist from database by id.
     *
     * @param id of Journalist
     * @return journalist
     * @returnType Journalist
     * @throws DataAccessException
     */
    Journalist getJournalistById(Integer id) throws DataAccessException;

    /**
     * Get journalist from database by name.
     *
     * @param name of Journalist
     * @return journalist
     * @returnType Journalist
     * @throws DataAccessException
     */
    Journalist getJournalistByName(String name) throws DataAccessException;

    /**
     * Add journalist to the database.
     *
     * @param journalist
     * @return number of rows successfully added
     * @returnType Intrger
     * @throws DataAccessException
     */
    Integer addJournalist (Journalist journalist) throws DataAccessException;

    /**
     * Updating journalist data in the database.
     *
     * @param journalist
     * @return number of rows successfully updated
     * @returnType Intrger
     * @throws DataAccessException
     */
    Integer updateJournalist(Journalist journalist) throws DataAccessException;

    /**
     * Delete journalist from database.
     *
     * @param id Journalist
     * @return number of rows successfully deleted
     * @returnType Intrger
     * @throws DataAccessException
     */
    Integer deleteJournalist(Integer id) throws DataAccessException;

}
