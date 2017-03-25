package com.kushnir.service;

import com.kushnir.dao.ArticleDao;
import com.kushnir.dao.JournalistDao;
import com.kushnir.model.Article;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;

/**
 * Article Service implementation
 */
@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    ArticleDao articleDao;

    @Autowired
    JournalistDao journalistDao;

    @Override
    public List<Article> getAllArticles(LocalDate createDateStart, LocalDate createDateEnd)
            throws IllegalArgumentException {
        LOGGER.debug("getAllArticles(createDateStart: " +createDateStart+ ", createDateEnd: " +createDateEnd+ ")");
        if ((createDateStart != null) && (createDateEnd != null)) {
            Assert.isTrue(
                    createDateStart.isBefore(createDateEnd)
                    ,"createDateStart must be before createDateEnd");
        }
        return articleDao.getAllArticles(createDateStart ,createDateEnd);
    }

    @Override
    public List<Article> getAllArticlesByJournalistId(Integer id) throws IllegalArgumentException {
        LOGGER.debug("getAllArticlesByJournalistId(id: {})", id);
        Assert.notNull(id, "The id must not be empty");
        Assert.isTrue(id > 0, "The id must be greater than zero");
        return articleDao.getAllArticlesByJournalistId(id);
    }

    @Override
    public Article getArticleById(Integer id) throws IllegalArgumentException {
        LOGGER.debug("getArticleById(id: {})", id);
        Assert.notNull(id, "The id must not be empty");
        Assert.isTrue(id > 0, "The id must be greater than zero");
        try {
            return articleDao.getArticleById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new IllegalArgumentException(
                    "Article by id("+id+") " +
                            " does not exist in the database");
        }
    }

    @Override
    public Article getArticleByNaim(String naim) throws IllegalArgumentException {
        LOGGER.debug("getArticleByNaim(naim: {})", naim);
        Assert.notNull(naim,"The naim must not be empty");
        Assert.hasText(naim,"The naim must contain text");
        try {
            return articleDao.getArticleByNaim(naim);
        } catch (EmptyResultDataAccessException e) {
            throw new IllegalArgumentException(
                    "Article by naim("+naim+") " +
                            " does not exist in the database");
        }
    }

    @Override
    public Integer addArticle(Article article) throws IllegalArgumentException {
        LOGGER.debug("addArticle(article: {})", article);
        Assert.notNull(article, "article must not be empty");
        Assert.isNull(article.getId(), "article id must be empty");
        Assert.notNull(article.getName(), "The naim must not be empty");
        Assert.hasText(article.getName(), "The naim must contain text");
        try {
            Assert.isNull(articleDao.getArticleByNaim(article.getName()),
                    "Article by naim (" + article.getName() + ") already exist in the database");
        } catch (EmptyResultDataAccessException e) {}

        Assert.notNull(article.getDateCreate(), "The DateCreate must not be empty");
        Assert.isTrue(article.getDateCreate().isBefore(LocalDate.now()), "The DateCreate must be before today");
        Assert.notNull(article.getPopularity(), "The Popularity must not be empty");
        Assert.notNull(article.getJournalistId(), "The JournalistId must not be empty");
        try {
            Assert.notNull(journalistDao.getJournalistById(article.getJournalistId()),
                    "Journalist by id("+article.getJournalistId()+") " +
                            "specified in the article, does not exist in the database");
        } catch (EmptyResultDataAccessException e) {
            throw new IllegalArgumentException(
                    "Journalist by id("+article.getJournalistId()+") " +
                            "specified in the article, does not exist in the database");
        }
        return articleDao.addArticle(article);
    }

    @Override
    public Integer updateArticle(Article article) throws IllegalArgumentException {
        LOGGER.debug("updateArticle(article: {})", article);
        Assert.notNull(article, "article must not be empty");
        Assert.notNull(article.getId(), "article id must not be empty");
        Assert.isTrue(article.getId() > 0, "article id must be greater than zero");
        try {
            Assert.notNull(articleDao.getArticleById(article.getId()),
                    "Article by id("+article.getId()+") " +
                            " does not exist in the database");
        } catch (EmptyResultDataAccessException e) {
            throw new IllegalArgumentException(
                    "Article by id("+article.getId()+") " +
                            " does not exist in the database");
        }

        Assert.notNull(article.getName(), "The naim must not be empty");
        Assert.notNull(article.getDateCreate(), "The DateCreate must not be empty");
        Assert.isTrue(article.getDateCreate().isBefore(LocalDate.now()), "The DateCreate must be before today");
        Assert.notNull(article.getPopularity(), "The Popularity must not be empty");
        Assert.notNull(article.getJournalistId(), "The JournalistId must not be empty");
        try {
            journalistDao.getJournalistById(article.getJournalistId());
        } catch (EmptyResultDataAccessException e) {
            throw new IllegalArgumentException(
                    "Journalist by id("+article.getJournalistId()+") " +
                            "specified in the article, does not exist in the database");
        }
        return articleDao.updateArticle(article);
    }

    @Override
    public Integer deleteArticle(Integer id) throws IllegalArgumentException {
        LOGGER.debug("deleteArticle(id: {})", id);
        Assert.notNull(id, "article id must not be empty");
        Assert.isTrue(id > 0, "id must be greater than zero");
        return articleDao.deleteArticle(id);
    }

    @Override
    public Integer deleteArticleByJournalistID(Integer id) throws IllegalArgumentException {
        LOGGER.debug("deleteArticleByJournalistID(id: {})", id);
        Assert.notNull(id, "id must not be empty");
        Assert.isTrue(id > 0, "id must be greater than zero");
        return articleDao.deleteArticleByJournalistID(id);
    }
}
