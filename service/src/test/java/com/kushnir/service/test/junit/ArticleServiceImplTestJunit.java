package com.kushnir.service.test.junit;

import com.kushnir.dao.ArticleDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.junit.*;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Junit Test of Article Service implementation
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class ArticleServiceImplTestJunit {

    private static final Logger LOGGER = LogManager.getLogger();

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

    // TODO: positive test getAllArticles ()
    // TODO: negative test getAllArticles ()

    // TODO: positive test getArticleById ()
    // TODO: negative test getArticleById ()

    // TODO: positive test getArticleByNaim ()
    // TODO: negative test getArticleByNaim ()

    // TODO: positive test addArticle ()
    // TODO: negative test addArticle ()

    // TODO: positive test updateArticle ()
    // TODO: negative test updateArticle ()

    // TODO: positive test deleteArticle ()
    // TODO: negative test deleteArticle ()

    // TODO: positive test deleteArticleByJournalistID ()
    // TODO: negative test deleteArticleByJournalistID ()
}
