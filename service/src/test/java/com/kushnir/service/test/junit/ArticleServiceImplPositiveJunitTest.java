package com.kushnir.service.test.junit;

import com.kushnir.model.Article;
import com.kushnir.service.ArticleService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.junit.*;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

/**
 * Junit Positive Test of Article Service implementation
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-service-test-config.xml"})
@Transactional
public class ArticleServiceImplPositiveJunitTest {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    ArticleService articleService;

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

    public static final Article ARTICLIE_WITH_ID_1 = new Article(
            1
            ,"Chechnya. BUSINESS ON EIGHTH MARCH"
            ,LocalDate.of(2005,12,5)
            , 100
            ,4);

    public static final Article NEW_ARTICLE = new Article(
            "New Article"
            ,LocalDate.of(2005,12,5)
            , 1
            ,1);

    @Test
    public void getAllArticlesPositiveTest () {
        LOGGER.debug("getAllArticlesPositiveTest()");
        List<Article> articleList =  articleService.getAllArticles(null,null);
        assertNotNull(articleList);
        assertTrue(articleList.size() == 4);
    }

    @Test
    public void getAllArticlesByJournalistIdPositiveTest () {
        LOGGER.debug("getAllArticlesByJournalistIdPositiveTest()");
        List<Article> articleList =  articleService.getAllArticlesByJournalistId(2);
        assertNotNull(articleList);
        assertTrue(articleList.size() == 2);
    }

    @Test
    public void getArticleByIdPositiveTest () {
        LOGGER.debug("getArticleByIdPositiveTest()");
        Article article = articleService.getArticleById(ARTICLIE_WITH_ID_1.getId());
        assertNotNull(article);
        assertTrue(article.equals(ARTICLIE_WITH_ID_1));
    }

    @Test
    public void getArticleByNaimPositiveTest () {
        LOGGER.debug("getArticleByNaimPositiveTest()");
        Article article = articleService.getArticleByNaim(ARTICLIE_WITH_ID_1.getName());
        assertNotNull(article);
        assertTrue(article.equals(ARTICLIE_WITH_ID_1));
    }

    @Test
    public void addArticlePositiveTest () {
        Integer countBefore = articleService.getAllArticles(null,null).size();
        Integer newArticleId = articleService.addArticle(NEW_ARTICLE);
        assertNotNull(newArticleId);
        assertTrue(newArticleId > 0);
        assertTrue(countBefore < articleService.getAllArticles(null,null).size());
        Article articleWhichWasAdded = articleService.getArticleById(newArticleId);
        assertTrue(NEW_ARTICLE.equals(articleWhichWasAdded));
    }

    @Test
    public void updateArticlePositiveTest () {
        Article articleBeforeUpdate = articleService.getArticleById(1);
        assertNotNull(articleBeforeUpdate);
        articleBeforeUpdate.setName("Naim Updated");
        articleBeforeUpdate.setPopularity(20);
        assertTrue(articleService.updateArticle(articleBeforeUpdate) == 1);
        Article articleAfterUpdate = articleService.getArticleById(articleBeforeUpdate.getId());
        assertNotNull(articleAfterUpdate);
        assertEquals(articleBeforeUpdate, articleAfterUpdate);
    }

    @Test
    public void deleteArticlePositiveTest () {
        Integer articleAddedForDeleteId = articleService.addArticle(NEW_ARTICLE);
        Integer countBefore = articleService.getAllArticles(null,null).size();
        assertTrue(articleService.deleteArticle(articleAddedForDeleteId) == 1);
        assertTrue(countBefore > articleService.getAllArticles(null,null).size());
        try {
            articleService.getArticleById(articleAddedForDeleteId);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().equals("Article by id("+articleAddedForDeleteId+") " +
                    " does not exist in the database"));
        }
    }

    @Test
    public void deleteArticleByJournalistIDPositiveTest () {
        Integer countBefore = articleService.getAllArticles(null,null).size();
        Integer countArticlesByJournalist = articleService.getAllArticlesByJournalistId(2).size();
        assertTrue(countArticlesByJournalist == 2);
        Integer expectedCountAfter = countBefore - countArticlesByJournalist;
        assertTrue(articleService.deleteArticleByJournalistID(2) == 2);
        assertTrue(expectedCountAfter == articleService.getAllArticles(null,null).size());
    }

}
