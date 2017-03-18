package com.kushnir.service;

import com.kushnir.model.Journalist;

import java.time.LocalDate;
import java.util.List;

/**
 * Journalist service API
 */
public interface JournalistService {

    /**
     * Get all journalists from database.
     *
     * @param birthDateStart
     * @param birthDateEnd
     * @return all journalists from DB
     * @returnType List<Journalist>
     */
    List<Journalist> getAllJournalists(LocalDate birthDateStart, LocalDate birthDateEnd);

    /**
     * Get journalist from database by id.
     *
     * @param id of Journalist
     * @return journalist
     * @returnType Journalist
     */
    Journalist getJournalistById(Integer id);

    /**
     * Get journalist from database by name.
     *
     * @param name of Journalist
     * @return journalist
     * @returnType Journalist
     */
    Journalist getJournalistByName(String name);

    /**
     * Add journalist to the database.
     *
     * @param journalist
     * @return number of rows successfully added
     * @returnType Integer
     */
    Integer addJournalist (Journalist journalist);

    /**
     * Updating journalist data in the database.
     *
     * @param journalist
     * @return number of rows successfully updated
     * @returnType Integer
     */
    Integer updateJournalist(Journalist journalist);

    /**
     * Delete journalist from database.
     *
     * @param id Journalist
     * @return number of rows successfully deleted
     * @returnType Integer
     */
    Integer deleteJournalist(Integer id);

}
