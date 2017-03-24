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
     * Check: birthDateStart must be before birthDateEnd
     *
     * @return all journalists from DB
     * @returnType List<Journalist>
     * @throws IllegalArgumentException
     */
    List<Journalist> getAllJournalists(LocalDate birthDateStart, LocalDate birthDateEnd);

    /**
     * Get journalist from database by id.
     *
     * @param id of Journalist
     * Check: The id must not be empty
     *        The id must be greater than zero
     *
     * @return journalist
     * @returnType Journalist
     * @throws IllegalArgumentException
     */
    Journalist getJournalistById(Integer id);

    /**
     * Get journalist from database by name.
     *
     * @param name of Journalist
     * Check: The name not be empty
     *        The name must contain text
     *
     * @return journalist
     * @returnType Journalist
     * @throws IllegalArgumentException
     */
    Journalist getJournalistByName(String name);

    /**
     * Add journalist to the database.
     *
     * @param journalist
     * Check: journalist must not be empty
     *        journalist id must be empty
     *        The name must not be empty
     *        The birthDate must not be empty
     *        The birthDate must be before today
     *        The rating must not be empty
     *
     * @return id of new journalist
     * @returnType Integer
     * @throws IllegalArgumentException
     */
    Integer addJournalist (Journalist journalist);

    /**
     * Updating journalist data in the database.
     *
     * @param journalist
     * Check: journalist must not be empty
     *        journalist id must not be empty
     *        The name must not be empty
     *        The birthDate must not be empty
     *        The birthDate must be before today
     *        The rating must not be empty
     *
     * @return number of rows successfully updated
     * @returnType Integer
     * @throws IllegalArgumentException
     */
    Integer updateJournalist(Journalist journalist);

    /**
     * Delete the journalist and his articles from the database.
     *
     * @param id Journalist
     * Check: id must not be empty
     *        id must be greater than zero
     *
     * @return number of rows successfully deleted
     * @returnType Integer
     * @throws IllegalArgumentException
     */
    Integer deleteJournalistById(Integer id);

}
