package com.kushnir.dao.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.kushnir.dao.JournalistDao;
import com.kushnir.model.Journalist;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Junit Tests for JournalistDaoImpl
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-spring-dao.xml"})
@Transactional
public class TestJournalistDaoImpl {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final Journalist JOURNALIST_NEW = new Journalist(
            "New Journalist",
            10,
            LocalDate.parse("1987-10-10"));

    private static final Journalist JOURNALIST_WITH_ID_1 = new Journalist(
            1,
            "Walter Cronkite",
            100,
            LocalDate.parse("1916-11-04"),
            0);

    @Autowired
    JournalistDao journalistDao;

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
    public void getAllJournalistsTest() throws Exception {
        List<Journalist> journalists = journalistDao.getAllJournalists(null, null);
        assertNotNull(journalists);
        assertTrue(journalists.size() > 0);
        LOGGER.debug("Test: getAllJournalists(), List<Journalist>: {}", journalists);
    }

    @Test
    public void getJournalistByIdTest() throws Exception {
        Journalist journalist = journalistDao.getJournalistById(JOURNALIST_WITH_ID_1.getId());
        assertNotNull(journalist);
        assertTrue(journalist.equals(JOURNALIST_WITH_ID_1));
        LOGGER.debug("Test: getJournalistById(" + JOURNALIST_WITH_ID_1.getId() + "), journalist: {}", journalist);
    }

    @Test
    public void getJournalistByNameTest() throws Exception {
        Journalist journalist = journalistDao.getJournalistByName(JOURNALIST_WITH_ID_1.getName());
        assertNotNull(journalist);
        assertTrue(journalist.equals(JOURNALIST_WITH_ID_1));
        LOGGER.debug("Test: getJournalistByName(" + JOURNALIST_WITH_ID_1.getName() + "), journalist: {}", journalist);
    }

    @Test
    public void addJournalistTest() {
        LOGGER.debug("Test: addJournalist()");
        int countJournalistsBefore = (journalistDao.getAllJournalists(null, null)).size();
        int newJournalistId = journalistDao.addJournalist(JOURNALIST_NEW);
        assertNotNull(newJournalistId);
        assertEquals(newJournalistId,5);
        assertTrue(countJournalistsBefore < journalistDao.getAllJournalists(null,null).size());
        journalistDao.deleteJournalist(newJournalistId);
    }

    @Test
    public void updateJournalistTest() {
        LOGGER.debug("Test: updateJournalist()");
        Journalist journalistBefore = journalistDao.getJournalistById(1);
        assertNotNull(journalistBefore);
        assertTrue(journalistDao.updateJournalist(
                new Journalist(journalistBefore.getId()
                        ,"Updated Name"
                        , 0
                        , LocalDate.of(1990,11,11))) > 0);
        Journalist journalistAfter = journalistDao.getJournalistById(journalistBefore.getId());
        assertNotNull(journalistAfter);
        assertFalse(journalistAfter.equals(journalistBefore));
    }

    @Test
    public void deleteJournalistTest() {
        LOGGER.debug("Test: deleteJournalist()");
        int newJournalistId = journalistDao.addJournalist(JOURNALIST_NEW);
        assertNotNull(newJournalistId);
        int countJournalistsBefore = (journalistDao.getAllJournalists(null, null)).size();
        assertTrue(journalistDao.deleteJournalist(newJournalistId) > 0);
        int countJournalistsAfter = journalistDao.getAllJournalists(null,null).size();
        assertTrue(countJournalistsBefore > countJournalistsAfter);

        try {
            assertNull(journalistDao.getJournalistById(newJournalistId));
        } catch (EmptyResultDataAccessException e) {

        }
    }

}
