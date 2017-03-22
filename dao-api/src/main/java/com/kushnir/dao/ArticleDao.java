package com.kushnir.dao;

import com.kushnir.model.Article;
import org.springframework.dao.DataAccessException;

import java.time.LocalDate;
import java.util.List;

/**
 * Article DAO interface
 */
public interface ArticleDao {

    /**
     * Get all articles from database by createDate.
     *
     * @param createDateStart
     * @param createDateEnd
     * @return all articles from DB
     * @returnType List<Article>
     * @throws DataAccessException
     */
    List<Article> getAllArticles(LocalDate createDateStart, LocalDate createDateEnd) throws DataAccessException;

    /**
     * Get all articles from database by Journalist id.
     *
     * @param id Journalist
     * @return articles from DB
     * @returnType List<Article>
     * @throws DataAccessException
     */
    List<Article> getAllArticlesByJournalistId (Integer id) throws DataAccessException;

    /**
     * Get article from database by id.
     *
     * @param id Article
     * @return Article
     * @returnType Article
     * @throws DataAccessException
     */
    Article getArticleById(Integer id) throws DataAccessException;

    /**
     * Get article from database by naim.
     *
     * @param naim Article
     * @return Article
     * @returnType Article
     * @throws DataAccessException
     */
    Article getArticleByNaim(String naim) throws DataAccessException;

    /**
     * Add article to the database.
     *
     * @param article
     * @return new article ID (autoincrement)
     * @returnType Intrger
     * @throws DataAccessException
     */
    Integer addArticle (Article article) throws DataAccessException;

    /**
     * Updating Article data in the database.
     *
     * @param article
     * @return number of rows successfully updated
     * @returnType Intrger
     * @throws DataAccessException
     */
    Integer updateArticle (Article article) throws DataAccessException;

    /**
     * Delete article from database.
     *
     * @param id article
     * @return number of rows successfully deleted
     * @returnType Intrger
     * @throws DataAccessException
     */
    Integer deleteArticle (Integer id) throws DataAccessException;

    /**
     * Delete article from database by Joournalist ID.
     *
     * @param id Journalist
     * @return number of rows successfully deleted
     * @returnType Intrger
     * @throws DataAccessException
     */
    Integer deleteArticleByJournalistID (Integer id) throws DataAccessException;

}
