package com.kushnir.dao.test;

import com.kushnir.dao.ArticleDao;
import com.kushnir.model.Article;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Junit Tests for ArticleDaoImpl
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-spring-dao.xml"})
@Transactional
public class TestArticleDaoImpl {

    private static final Article ARTICLE_NEW = new Article(
            "New Article"
            , LocalDate.now()
            ,1
            ,1);

    private static final Article ARTICLE_WITH_ID_1 = new Article(
            1
            ,"Chechnya. BUSINESS ON \"EIGHTH MARCH"
            , LocalDate.parse("2005-12-05")
            ,100
            ,4);

    private static final Article ARTICLE_WITH_ID_1_TO_UPDATE = new Article(
            1
            ,"Updated Article"
            , LocalDate.now()
            , 0
            ,1);

    @Autowired
    ArticleDao articleDao;

    @Test
    public void getAllArticlesTest () {
        List<Article> articleList = articleDao.getAllArticles(null,null);
        assertNotNull(articleList);
        assertTrue(articleList.size() > 0);
    }

    // TODO getAllArticlesWithDetails method test

    @Test
    public void getArticleByIdTest () {
        Article article = articleDao.getArticleById(ARTICLE_WITH_ID_1.getId());
        assertNotNull(article);
        assertTrue(article.equals(ARTICLE_WITH_ID_1));
    }

    @Test
    public void getArticleByNaimTest () {
        Article article = articleDao.getArticleByNaim(ARTICLE_WITH_ID_1.getName());
        assertNotNull(article);
        assertTrue(article.equals(ARTICLE_WITH_ID_1));
    }

    @Test
    public void addArticleTest () {
        int countArticlesBefore = (articleDao.getAllArticles(null, null)).size();
        int newArticleId = articleDao.addArticle(ARTICLE_NEW);
        assertNotNull(newArticleId);
        assertEquals(newArticleId,6);
        assertTrue(countArticlesBefore < articleDao.getAllArticles(null,null).size());
        articleDao.deleteArticle(newArticleId);
    }

    @Test
    public void updateArticleTest () {
        Article articleBefore = articleDao.getArticleById(1);
        assertNotNull(articleBefore);
        assertTrue(articleDao.updateArticle(ARTICLE_WITH_ID_1_TO_UPDATE) > 0);

        Article articleAfter = articleDao.getArticleById(articleBefore.getId());
        assertNotNull(articleAfter);
        assertTrue(!articleAfter.equals(articleBefore));
    }

    @Test
    public void deleteArticleTest () {
        int newArticleId = articleDao.addArticle(ARTICLE_NEW);
        assertNotNull(newArticleId);

        int countArticlesBefore = articleDao.getAllArticles(null, null).size();
        assertTrue(articleDao.deleteArticle(newArticleId) == 1);
        int countArticlesAfter = articleDao.getAllArticles(null,null).size();

        assertTrue(countArticlesBefore > countArticlesAfter);
        try {
            assertNull(articleDao.getArticleById(newArticleId));
        } catch (EmptyResultDataAccessException e) {

        }
    }

}
