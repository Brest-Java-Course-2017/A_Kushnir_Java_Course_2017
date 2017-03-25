package com.kushnir.service.test.junit;

import com.kushnir.model.Article;
import com.kushnir.model.Journalist;
import com.kushnir.service.ArticleService;
import com.kushnir.service.JournalistService;
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
import java.util.List;

/**
 * Junit Positive Test of Journalist Service implementation
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-service-test-config.xml"})
@Transactional
public class JournalistServiceImplPositiveJunitTest {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final Journalist JOURNALIST_NEW = new Journalist(
            null
            ,"New Journalist"
            ,10
            ,LocalDate.of(1987,10,10)
            ,0);

    private static final Article ARTICLE_BY_NEW_JOURNALIST = new Article(
            null
            ,"Sample article"
            , LocalDate.of(2005,2,22)
            , 100
            , JOURNALIST_NEW.getId());

    private static final Journalist JOURNALIST_WITH_ID_1 = new Journalist(
            1
            ,"Walter Cronkite"
            ,100
            ,LocalDate.of(1916,11,04)
            ,0);

    @Autowired
    JournalistService journalistService;

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


    @Test
    public void getAllJournalistsPositiveTest() throws Exception {
        LOGGER.debug("getAllJournalistsPositiveTest()");
        List<Journalist> journalistsList = journalistService.getAllJournalists(null, null);
        assertNotNull(journalistsList);
        assertTrue(journalistsList.size() == 4);
    }

    @Test
    public void getJournalistByIdPositiveTest() throws Exception {
        LOGGER.debug("getJournalistByIdPositiveTest()");
        Journalist journalist = journalistService.getJournalistById(JOURNALIST_WITH_ID_1.getId());
        assertNotNull(journalist);
        assertTrue(JOURNALIST_WITH_ID_1.equals(journalist));
    }

    @Test
    public void getJournalistByNamePositiveTest() throws Exception {
        LOGGER.debug("getJournalistByNamePositiveTest()");
        Journalist journalist = journalistService.getJournalistByName(JOURNALIST_WITH_ID_1.getName());
        assertNotNull(journalist);
        assertTrue(JOURNALIST_WITH_ID_1.equals(journalist));
    }

    @Test
    public void addJournalistPositiveTest() throws Exception {
        LOGGER.debug("addJournalistPositiveTest()");
        int countBefore = journalistService.getAllJournalists(null, null).size();
        int idAddedJournalist = journalistService.addJournalist(JOURNALIST_NEW);
        assertTrue(idAddedJournalist > 0);
        JOURNALIST_NEW.setId(idAddedJournalist);
        assertTrue(countBefore < journalistService.getAllJournalists(null, null).size());
        assertTrue(JOURNALIST_NEW.equals(journalistService.getJournalistById(idAddedJournalist)));
    }

    @Test
    public void updateJournalistPositiveTest() throws Exception {
        LOGGER.debug("updateJournalistPositiveTest()");
        Journalist journalistForUpdate = journalistService.getJournalistById(JOURNALIST_WITH_ID_1.getId());
        assertNotNull(journalistForUpdate);

        journalistForUpdate.setName("Updated Name");
        journalistForUpdate.setBirthDate(LocalDate.of(1990,12,10));
        journalistForUpdate.setRate(2);

        assertTrue(journalistService.updateJournalist(journalistForUpdate) == 1);
        assertTrue(journalistForUpdate.equals(journalistService.getJournalistById(journalistForUpdate.getId())));
    }

    @Test
    public void deleteJournalistPositiveTest() throws Exception {
        LOGGER.debug("deleteJournalistPositiveTest()");

        int newJournalistId = journalistService.addJournalist(JOURNALIST_NEW);
        ARTICLE_BY_NEW_JOURNALIST.setJournalistId(newJournalistId);
        int articleAddedId = articleService.addArticle(ARTICLE_BY_NEW_JOURNALIST);
        assertTrue(newJournalistId > 0 && articleAddedId > 0);

        int countBefore = journalistService.getAllJournalists(null, null).size();
        assertTrue(journalistService.deleteJournalistById(newJournalistId) == 1);
        assertTrue(countBefore > journalistService.getAllJournalists(null, null).size());
        try {
            journalistService.getJournalistById(newJournalistId);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().equals("Journalist by id("+newJournalistId+") " +
                    " does not exist in the database"));
        }
        // Check for articles belonging to deleted journalist
        assertTrue(articleService.getAllArticlesByJournalistId(newJournalistId).size() == 0);
    }

}
