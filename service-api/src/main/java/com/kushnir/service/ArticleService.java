package com.kushnir.service;

import com.kushnir.model.Article;

import java.time.LocalDate;
import java.util.List;

/**
 * Article Service API
 */
public interface ArticleService {

    /**
     * Get all articles from database.
     *
     * @param createDateStart
     * @param createDateEnd
     * Check: createDateStart must be before createDateEnd
     *
     * @return all articles from DB
     * @returnType List<Article>
     * @throws IllegalArgumentException
     */
    List<Article> getAllArticles(LocalDate createDateStart, LocalDate createDateEnd) throws IllegalArgumentException;

    /**
     * Get all articles from database.
     *
     * @param id Journalist
     * Check: The id must not be empty
     *        The id must be greater than zero
     *
     * @return all articles from DB by journalist
     * @returnType List<Article>
     * @throws IllegalArgumentException
     */
    List<Article> getAllArticlesByJournalistId(Integer id);

    /**
     * Get article from database by id.
     *
     * @param id Article
     * Check: The id must not be empty
     *        The id must be greater than zero
     *
     * @return Article
     * @returnType Article
     * @throws IllegalArgumentException
     */
    Article getArticleById(Integer id);

    /**
     * Get article from database by naim.
     *
     * @param naim Article
     * Check: The naim must not be empty
     *        The naim must contain text
     *
     * @return Article
     * @returnType Article
     * @throws IllegalArgumentException
     */
    Article getArticleByNaim(String naim);

    /**
     * Add article to the database.
     *
     * @param article
     * Check: article must not be empty
     *        article id must be empty
     *        The naim must not be empty
     *        The DateCreate must not be empty
     *        The DateCreate must be before today
     *        The Popularity must not be empty
     *        The JournalistId must not be empty
     *
     * @return id of new Article
     * @returnType Integer
     * @throws IllegalArgumentException
     */
    Integer addArticle (Article article);

    /**
     * Updating Article data in the database.
     *
     * @param article
     * Check: article must not be empty
     *        article id must not be empty
     *        The naim must not be empty
     *        The DateCreate must not be empty
     *        The DateCreate must be before today
     *        The Popularity must not be empty
     *        The JournalistId must not be empty
     *
     * @return number of rows successfully updated
     * @returnType Integer
     * @throws IllegalArgumentException
     */
    Integer updateArticle (Article article);

    /**
     * Delete article from database.
     *
     * @param id article
     * Check: id must not be empty
     *        id must be greater than zero
     *
     * @return number of rows successfully deleted
     * @returnType Integer
     * @throws IllegalArgumentException
     */
    Integer deleteArticle (Integer id);

    /**
     * Delete article from database by Journalist ID.
     *
     * @param id Journalist
     * Check: id must not be empty
     *        id must be greater than zero
     *
     * @return number of rows successfully deleted
     * @returnType Integer
     * @throws IllegalArgumentException
     */
    Integer deleteArticleByJournalistID (Integer id);

}
