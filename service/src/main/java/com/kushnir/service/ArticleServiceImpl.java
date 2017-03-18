package com.kushnir.service;

import com.kushnir.dao.ArticleDao;
import com.kushnir.model.Article;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;

/**
 * Article Service implementation
 */
public class ArticleServiceImpl implements ArticleService {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    ArticleDao articleDao;

    @Override
    public List<Article> getAllArticles(LocalDate createDateStart, LocalDate createDateEnd) {
        LOGGER.debug("getAllArticles(createDateStart: " +createDateStart+ ", createDateEnd: " +createDateEnd+ ")");
        if ((createDateStart != null) && (createDateEnd != null)) {
            Assert.isTrue(
                    createDateStart.isBefore(createDateEnd)
                    ,"createDateStart must be before createDateEnd");
        }

        return articleDao.getAllArticles(createDateStart ,createDateEnd);
    }

    @Override
    public Article getArticleById(Integer id) {
        LOGGER.debug("getArticleById(id: {})", id);
        Assert.notNull(id, "The id must not be empty");
        Assert.isTrue(id > 0, "The id must be greater than zero");
        return articleDao.getArticleById(id);
    }

    @Override
    public Article getArticleByNaim(String naim) {
        LOGGER.debug("getArticleByNaim(naim: {})", naim);
        Assert.notNull(naim,"The naim must not be empty");
        Assert.hasText(naim,"The naim must contain text");
        return articleDao.getArticleByNaim(naim);
    }

    @Override
    public Integer addArticle(Article article) {
        LOGGER.debug("addArticle(article: {})", article);
        Assert.notNull(article, "article must be empty");
        Assert.isNull(article.getId(), "article id must be empty");
        Assert.notNull(article.getName(), "The naim must not be empty");
        Assert.notNull(article.getDateCreate(), "The DateCreate must not be empty");
        Assert.isTrue(article.getDateCreate().isBefore(LocalDate.now()), "The DateCreate must be before today");
        Assert.notNull(article.getPopularity(), "The Popularity must not be empty");
        Assert.notNull(article.getJournalistId(), "The JournalistId must not be empty");

        return articleDao.addArticle(article);
    }

    @Override
    public Integer updateArticle(Article article) {
        LOGGER.debug("updateArticle(article: {})", article);
        Assert.notNull(article);
        Assert.notNull(article.getId(), "article id must not be empty");
        Assert.isTrue(article.getId() > 0, "article id must be greater than zero");
        Assert.notNull(article.getName(), "The naim must not be empty");
        Assert.notNull(article.getDateCreate(), "The DateCreate must not be empty");
        Assert.isTrue(article.getDateCreate().isBefore(LocalDate.now()), "The DateCreate must be before today");
        Assert.notNull(article.getPopularity(), "The Popularity must not be empty");
        Assert.notNull(article.getJournalistId(), "The JournalistId must not be empty");

        return articleDao.updateArticle(article);
    }

    @Override
    public Integer deleteArticle(Integer id) {
        LOGGER.debug("deleteArticle(id: {})", id);
        Assert.notNull(id, "article id must not be empty");
        Assert.isTrue(id > 0, "id must be greater than zero");
        return articleDao.deleteArticle(id);
    }

    @Override
    public Integer deleteArticleByJournalistID(Integer id) {
        LOGGER.debug("deleteArticleByJournalistID(id: {})", id);
        Assert.notNull(id, "id must not be empty");
        Assert.isTrue(id > 0, "id must be greater than zero");
        return articleDao.deleteArticleByJournalistID(id);
    }
}
