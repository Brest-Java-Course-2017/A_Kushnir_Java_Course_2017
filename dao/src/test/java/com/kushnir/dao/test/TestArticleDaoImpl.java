package com.kushnir.dao.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.kushnir.dao.ArticleDao;
import com.kushnir.model.Article;

import org.junit.*;
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

    private static final Logger LOGGER = LogManager.getLogger();

    private static final Article ARTICLE_NEW = new Article(
            "New Article"
            , LocalDate.now()
            ,1
            ,1);

    private static final Article ARTICLE_WITH_ID_1 = new Article(
            1
            ,"Chechnya. BUSINESS ON EIGHTH MARCH"
            , LocalDate.parse("2005-12-05")
            ,100
            ,4);

    private static final Article ARTICLE_WITH_ID_1_TO_UPDATE = new Article(
            1
            ,"Updated Article"
            , LocalDate.now()
            , 0
            ,1);

    private static final Integer JOURNALIST_ID = 2;

    @Autowired
    ArticleDao articleDao;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        LOGGER.debug("execute: setUpBeforeClass()");
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        LOGGER.debug("execute: tearDownAfterClass()");
    }

    @Before
    public void beforeTest() {
        LOGGER.debug("execute: beforeTest()");
    }

    @After
    public void afterTest() {
        LOGGER.debug("execute: afterTest()");
    }

    @Test
    public void getAllArticlesTest () {
        List<Article> articleList = articleDao.getAllArticles(null,null);
        assertNotNull(articleList);
        assertTrue(articleList.size() > 0);
        LOGGER.debug("Test: getAllArticles(), List<Article>: {}", articleList);
    }

    @Test
    public void getAllArticlesByJournalistIdTest () {
        List<Article> articleList = articleDao.getAllArticlesByJournalistId(JOURNALIST_ID);
        assertNotNull(articleList);
        assertTrue(articleList.size() > 0);
        LOGGER.debug("Test: getAllArticlesByJournalistIdTest(), List<Article>: {}", articleList);
    }

    @Test
    public void getArticleByIdTest () {
        Article article = articleDao.getArticleById(ARTICLE_WITH_ID_1.getId());
        assertNotNull(article);
        assertTrue(article.equals(ARTICLE_WITH_ID_1));
        LOGGER.debug("Test: getArticleById(" + ARTICLE_WITH_ID_1.getId() + "), Article: {}", article);
    }

    @Test
    public void getArticleByNaimTest () {
        Article article = articleDao.getArticleByNaim(ARTICLE_WITH_ID_1.getName());
        assertNotNull(article);
        assertTrue(article.equals(ARTICLE_WITH_ID_1));
        LOGGER.debug("Test: getArticleByNaim(" + ARTICLE_WITH_ID_1.getName() + "), Article: {}", article);
    }

    @Test
    public void addArticleTest () {
        LOGGER.debug("Test: addArticle()");
        int countArticlesBefore = (articleDao.getAllArticles(null, null)).size();
        int newArticleId = articleDao.addArticle(ARTICLE_NEW);
        assertNotNull(newArticleId);
        assertEquals(newArticleId,6);
        assertTrue(countArticlesBefore < articleDao.getAllArticles(null,null).size());
        articleDao.deleteArticle(newArticleId);
    }

    @Test
    public void updateArticleTest () {
        LOGGER.debug("Test: updateArticle()");
        Article articleBefore = articleDao.getArticleById(1);
        assertNotNull(articleBefore);
        assertTrue(articleDao.updateArticle(ARTICLE_WITH_ID_1_TO_UPDATE) > 0);

        Article articleAfter = articleDao.getArticleById(articleBefore.getId());
        assertNotNull(articleAfter);
        assertTrue(!articleAfter.equals(articleBefore));
    }

    @Test
    public void deleteArticleTest () {
        LOGGER.debug("Test: deleteArticle()");
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
