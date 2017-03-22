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
     *
     * Check: createDateStart must be before createDateEnd
     *
     * @return all articles from DB
     * @returnType List<Article>
     */
    List<Article> getAllArticles(LocalDate createDateStart, LocalDate createDateEnd);

    /**
     * Get all articles from database.
     *
     * @param id Journalist
     *
     * Check: The id must not be empty
     *        The id must be greater than zero
     *
     * @return all articles from DB by journalist
     * @returnType List<Article>
     */
    List<Article> getAllArticlesByJournalistId(Integer id);

    /**
     * Get article from database by id.
     *
     * @param id Article
     *
     * Check: The id must not be empty
     *        The id must be greater than zero
     *
     * @return Article
     * @returnType Article
     */
    Article getArticleById(Integer id);

    /**
     * Get article from database by naim.
     *
     * Check: The naim must not be empty
     *        The naim must contain text
     *
     * @param naim Article
     * @return Article
     * @returnType Article
     */
    Article getArticleByNaim(String naim);

    /**
     * Add article to the database.
     *
     * Check: article must not be empty
     *        article id must be empty
     *        The naim must not be empty
     *        The DateCreate must not be empty
     *        The DateCreate must be before today
     *        The Popularity must not be empty
     *        The JournalistId must not be empty
     *
     * @param article
     * @return number of rows successfully added
     * @returnType Intrger
     */
    Integer addArticle (Article article);

    /**
     * Updating Article data in the database.
     *
     * Check: article must not be empty
     *        article id must not be empty
     *        The naim must not be empty
     *        The DateCreate must not be empty
     *        The DateCreate must be before today
     *        The Popularity must not be empty
     *        The JournalistId must not be empty
     *
     * @param article
     * @return number of rows successfully updated
     * @returnType Intrger
     */
    Integer updateArticle (Article article);

    /**
     * Delete article from database.
     *
     * Check: id must not be empty
     *        id must be greater than zero
     *
     * @param id article
     * @return number of rows successfully deleted
     * @returnType Intrger
     */
    Integer deleteArticle (Integer id);

    /**
     * Delete article from database by Joournalist ID.
     *
     * Check: id must not be empty
     *        id must be greater than zero
     *
     * @param id Journalist
     * @return number of rows successfully deleted
     * @returnType Intrger
     */
    Integer deleteArticleByJournalistID (Integer id);

}
