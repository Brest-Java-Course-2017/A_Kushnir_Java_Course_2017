package com.kushnir.service.test.junit;

import com.kushnir.model.Article;
import com.kushnir.service.ArticleService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.junit.*;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

/**
 * Junit Negative Test of Article Service implementation
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-service-test-config.xml"})
@Transactional
public class ArticleServiceImplNegativeTestJunit {

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

    public static final Article NEW_ARTICLE = new Article(
            "New Article"
            ,LocalDate.of(2005,12,5)
            , 1
            ,1);

    public static final Article ILLEGAL_ARTICLE = new Article(
            10
            ,null
            ,null
            ,null
            ,null);

    @Test(expected=IllegalArgumentException.class)
    public void getAllArticlesNegativeTest () {
        LOGGER.debug("getAllArticlesNegativeTest()");
        articleService.getAllArticles(LocalDate.of(1990,1,11), LocalDate.of(1989,1,11));
    }

    @Test
    public void getAllArticlesByJournalistIdNegativeTest () {
        LOGGER.debug("getAllArticlesByJournalistIdNegativeTest()");
        try {
            articleService.getAllArticlesByJournalistId(null);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().equals("The id must not be empty"));
        }
        try {
            articleService.getAllArticlesByJournalistId(0);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().equals("The id must be greater than zero"));
        }

        articleService.getAllArticlesByJournalistId(100);
    }

    @Test
    public void getArticleByIdNegativeTest () {
        LOGGER.debug("getArticleByIdNegativeTest()");
        try {
            articleService.getArticleById(null);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().equals("The id must not be empty"));
        }
        try {
            articleService.getArticleById(0);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().equals("The id must be greater than zero"));
        }
        try {
            articleService.getArticleById(999);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().equals("Article by id("+999+") " +
                    " does not exist in the database"));
        }
    }

    @Test
    public void getArticleByNaimNegativeTest () {
        LOGGER.debug("getArticleByNaimNegativeTest()");
        try {
            articleService.getArticleByNaim(null);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().equals("The naim must not be empty"));
        }
        try {
            articleService.getArticleByNaim("");
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().equals("The naim must contain text"));
        }
        try {
            articleService.getArticleByNaim(NEW_ARTICLE.getName());
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().equals("Article by naim("+NEW_ARTICLE.getName()+") " +
                    " does not exist in the database"));
        }
    }

    @Test
    public void addArticleNegativeTest () {
        try {
            articleService.addArticle(null);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {assertTrue(e.getMessage().equals("article must not be empty"));}

        try {
            articleService.addArticle(ILLEGAL_ARTICLE);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {assertTrue(e.getMessage().equals("article id must be empty"));}
        ILLEGAL_ARTICLE.setId(null);
        ILLEGAL_ARTICLE.setName(null);

        try {
            articleService.addArticle(ILLEGAL_ARTICLE);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {assertTrue(e.getMessage().equals("The naim must not be empty"));}
        ILLEGAL_ARTICLE.setName("Chechnya. BUSINESS ON EIGHTH MARCH");

        try {
            articleService.addArticle(ILLEGAL_ARTICLE);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {assertTrue(e.getMessage().equals(
                "Article by naim (" + ILLEGAL_ARTICLE.getName() + ") already exist in the database"));}
        ILLEGAL_ARTICLE.setName("New Naim");
        ILLEGAL_ARTICLE.setDateCreate(null);

        try {
            articleService.addArticle(ILLEGAL_ARTICLE);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {assertTrue(e.getMessage().equals("The DateCreate must not be empty"));}
        ILLEGAL_ARTICLE.setDateCreate(LocalDate.now().plusDays(1));

        try {
            articleService.addArticle(ILLEGAL_ARTICLE);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {assertTrue(e.getMessage().equals("The DateCreate must be before today"));}
        ILLEGAL_ARTICLE.setDateCreate(LocalDate.of(1990,5,5));
        ILLEGAL_ARTICLE.setPopularity(null);

        try {
            articleService.addArticle(ILLEGAL_ARTICLE);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {assertTrue(e.getMessage().equals("The Popularity must not be empty"));}
        ILLEGAL_ARTICLE.setPopularity(20);
        ILLEGAL_ARTICLE.setJournalistId(null);

        try {
            articleService.addArticle(ILLEGAL_ARTICLE);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {assertTrue(e.getMessage().equals("The JournalistId must not be empty"));}
        ILLEGAL_ARTICLE.setJournalistId(100);

        try {
            articleService.addArticle(ILLEGAL_ARTICLE);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {assertTrue(e.getMessage().equals(
                "Journalist by id("+ILLEGAL_ARTICLE.getJournalistId()+")" +
                        " specified in the article, does not exist in the database"));
        }
    }

    @Test
    public void updateArticleNegativeTest () {
        try {
            articleService.updateArticle(null);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {assertTrue(e.getMessage().equals("article must not be empty"));}
        ILLEGAL_ARTICLE.setId(null);

        try {
            articleService.updateArticle(ILLEGAL_ARTICLE);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {assertTrue(e.getMessage().equals("article id must not be empty"));}
        ILLEGAL_ARTICLE.setId(0);

        try {
            articleService.updateArticle(ILLEGAL_ARTICLE);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {assertTrue(e.getMessage().equals("article id must be greater than zero"));}
        ILLEGAL_ARTICLE.setId(100);

        try {
            articleService.updateArticle(ILLEGAL_ARTICLE);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {assertTrue(e.getMessage().equals(
                "Article by id("+ILLEGAL_ARTICLE.getId()+") " +
                        " does not exist in the database"));
        }
        ILLEGAL_ARTICLE.setId(1);
        ILLEGAL_ARTICLE.setName(null);

        try {
            articleService.updateArticle(ILLEGAL_ARTICLE);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {assertTrue(e.getMessage().equals("The naim must not be empty"));}
        ILLEGAL_ARTICLE.setName("Updated Naim");
        ILLEGAL_ARTICLE.setDateCreate(null);

        try {
            articleService.updateArticle(ILLEGAL_ARTICLE);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {assertTrue(e.getMessage().equals("The DateCreate must not be empty"));}
        ILLEGAL_ARTICLE.setDateCreate(LocalDate.now().plusDays(1));

        try {
            articleService.updateArticle(ILLEGAL_ARTICLE);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {assertTrue(e.getMessage().equals("The DateCreate must be before today"));}
        ILLEGAL_ARTICLE.setDateCreate(LocalDate.of(1990,5,5));
        ILLEGAL_ARTICLE.setPopularity(null);

        try {
            articleService.updateArticle(ILLEGAL_ARTICLE);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {assertTrue(e.getMessage().equals("The Popularity must not be empty"));}
        ILLEGAL_ARTICLE.setPopularity(20);
        ILLEGAL_ARTICLE.setJournalistId(null);

        try {
            articleService.updateArticle(ILLEGAL_ARTICLE);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {assertTrue(e.getMessage().equals("The JournalistId must not be empty"));}
        ILLEGAL_ARTICLE.setJournalistId(100);

        try {
            articleService.updateArticle(ILLEGAL_ARTICLE);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {assertTrue(e.getMessage().equals(
                "Journalist by id("+ILLEGAL_ARTICLE.getJournalistId()+")" +
                        " specified in the article, does not exist in the database"));
        }
    }

    @Test
    public void deleteArticleNegativeTest () {
        LOGGER.debug("deleteArticleNegativeTest()");
        try {
            articleService.deleteArticle(null);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().equals("article id must not be empty"));
        }
        try {
            articleService.deleteArticle(0);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().equals("id must be greater than zero"));
        }

        //articleService.deleteArticle(999);
        //fail("Expected an IllegalArgumentException to be thrown");
    }

    @Test
    public void deleteArticleByJournalistIDNegativeTest () {
        LOGGER.debug("deleteArticleByJournalistIDNegativeTest()");
        try {
            articleService.deleteArticleByJournalistID(null);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {assertTrue(e.getMessage().equals("id must not be empty"));}

        try {
            articleService.deleteArticleByJournalistID(0);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {assertTrue(e.getMessage().equals("id must be greater than zero"));}

        //articleService.deleteArticleByJournalistID(999);
        //fail("Expected an IllegalArgumentException to be thrown");
    }
}
