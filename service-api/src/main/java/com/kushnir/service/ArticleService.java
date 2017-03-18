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
     * @return all articles from DB
     * @returnType List<Article>
     */
    List<Article> getAllArticles(LocalDate createDateStart, LocalDate createDateEnd);

    /**
     * Get article from database by id.
     *
     * @param id Article
     * @return Article
     * @returnType Article
     */
    Article getArticleById(Integer id);

    /**
     * Get article from database by naim.
     *
     * @param naim Article
     * @return Article
     * @returnType Article
     */
    Article getArticleByNaim(String naim);

    /**
     * Add article to the database.
     *
     * @param article
     * @return number of rows successfully added
     * @returnType Intrger
     */
    Integer addArticle (Article article);

    /**
     * Updating Article data in the database.
     *
     * @param article
     * @return number of rows successfully updated
     * @returnType Intrger
     */
    Integer updateArticle (Article article);

    /**
     * Delete article from database.
     *
     * @param id article
     * @return number of rows successfully deleted
     * @returnType Intrger
     */
    Integer deleteArticle (Integer id);

    /**
     * Delete article from database by Joournalist ID.
     *
     * @param id Journalist
     * @return number of rows successfully deleted
     * @returnType Intrger
     */
    Integer deleteArticleByJournalistID (Integer id);

}
