package com.kushnir.service.test.junit;

import com.kushnir.model.Journalist;
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

/**
 * Junit Negative Test of Journalist Service implementation
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-service-test-config.xml"})
@Transactional
public class JournalistServiceImplNegativeJunitTest {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    JournalistService journalistService;

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

    public static  final Journalist NEW_ILLEGAL_JOURNALIST_TO_ADD = new Journalist(
            1
            ,null
            ,null
            ,null);

    public static  final Journalist NEW_ILLEGAL_JOURNALIST_TO_UPDATE = new Journalist(
            null
            ,null
            ,null
            ,null);


    @Test(expected=IllegalArgumentException.class)
    public void getAllJournalistsNegativeTest() throws Exception {
        LOGGER.debug("getAllJournalistsNegativeTest()");
        journalistService.getAllJournalists(LocalDate.of(1990,1,11), LocalDate.of(1989,1,11));
    }

    @Test
    public void getJournalistByIdNegativeTest() throws Exception {
        try {
            journalistService.getJournalistById(null);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e){
            assertTrue(e.getMessage().equals("The id must not be empty"));
        }
        try {
            journalistService.getJournalistById(0);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e){
            assertTrue(e.getMessage().equals("The id must be greater than zero"));
        }
        try {
            journalistService.getJournalistById(999);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e){
            assertTrue(e.getMessage().equals("Journalist by id("+999+") " +
                    " does not exist in the database"));
        }
    }

    @Test
    public void getJournalistByNameNegativeTest() throws Exception {
        try {
            journalistService.getJournalistByName(null);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().equals("The name not be empty"));
        }
        try {
            journalistService.getJournalistByName("");
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().equals("The name must contain text"));
        }
        try {
            journalistService.getJournalistByName("Fail Name");
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().equals("Journalist by name(Fail Name) " +
                    " does not exist in the database"));
        }
    }

    @Test
    public void addJournalistNegativeTest() throws Exception {
        try {
            journalistService.addJournalist(null);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().equals("journalist must not be empty"));
        }

        try {
            journalistService.addJournalist(NEW_ILLEGAL_JOURNALIST_TO_ADD);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().equals("journalist id must be empty"));
        }

        NEW_ILLEGAL_JOURNALIST_TO_ADD.setId(null);
        try {
            journalistService.addJournalist(NEW_ILLEGAL_JOURNALIST_TO_ADD);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().equals("The name must not be empty"));
        }

        NEW_ILLEGAL_JOURNALIST_TO_ADD.setName("");
        try {
            journalistService.addJournalist(NEW_ILLEGAL_JOURNALIST_TO_ADD);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().equals("The name must contain text"));
        }

        NEW_ILLEGAL_JOURNALIST_TO_ADD.setName("Walter Cronkite");
        try {
            journalistService.addJournalist(NEW_ILLEGAL_JOURNALIST_TO_ADD);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().equals("journalist by name (Walter Cronkite) already exist in the database"));
        }

        NEW_ILLEGAL_JOURNALIST_TO_ADD.setName("New Sample name");
        try {
            journalistService.addJournalist(NEW_ILLEGAL_JOURNALIST_TO_ADD);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().equals("The birthDate must not be empty"));
        }

        NEW_ILLEGAL_JOURNALIST_TO_ADD.setBirthDate(LocalDate.of(2018,5,5));
        try {
            journalistService.addJournalist(NEW_ILLEGAL_JOURNALIST_TO_ADD);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().equals("The birthDate must be before today"));
        }

        NEW_ILLEGAL_JOURNALIST_TO_ADD.setBirthDate(LocalDate.of(1970,5,5));
        try {
            journalistService.addJournalist(NEW_ILLEGAL_JOURNALIST_TO_ADD);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().equals("The rating must not be empty"));
        }
    }

    @Test
    public void updateJournalistNegativeTest() throws Exception {
        try {
            journalistService.updateJournalist(null);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().equals("journalist must not be empty"));
        }

        try {
            journalistService.updateJournalist(NEW_ILLEGAL_JOURNALIST_TO_UPDATE);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().equals("journalist id must not be empty"));
        }

        NEW_ILLEGAL_JOURNALIST_TO_UPDATE.setId(0);
        try {
            journalistService.updateJournalist(NEW_ILLEGAL_JOURNALIST_TO_UPDATE);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().equals("journalist id must be greater than zero"));
        }

        NEW_ILLEGAL_JOURNALIST_TO_UPDATE.setId(999);
        try {
            journalistService.updateJournalist(NEW_ILLEGAL_JOURNALIST_TO_UPDATE);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().equals("journalist by id(999) " +
                    " does not exist in the database"));
        }

        NEW_ILLEGAL_JOURNALIST_TO_UPDATE.setId(1);
        try {
            journalistService.updateJournalist(NEW_ILLEGAL_JOURNALIST_TO_UPDATE);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().equals("The name must not be empty"));
        }

        NEW_ILLEGAL_JOURNALIST_TO_UPDATE.setName("");
        try {
            journalistService.updateJournalist(NEW_ILLEGAL_JOURNALIST_TO_UPDATE);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().equals("The name must contain text"));
        }

        NEW_ILLEGAL_JOURNALIST_TO_UPDATE.setName("Updated Name");
        try {
            journalistService.updateJournalist(NEW_ILLEGAL_JOURNALIST_TO_UPDATE);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().equals("The birthDate must not be empty"));
        }

        NEW_ILLEGAL_JOURNALIST_TO_UPDATE.setBirthDate(LocalDate.of(2018,5,5));
        try {
            journalistService.updateJournalist(NEW_ILLEGAL_JOURNALIST_TO_UPDATE);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().equals("The birthDate must be before today"));
        }

        NEW_ILLEGAL_JOURNALIST_TO_UPDATE.setBirthDate(LocalDate.of(1960,5,5));
        try {
            journalistService.updateJournalist(NEW_ILLEGAL_JOURNALIST_TO_UPDATE);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().equals("The rating must not be empty"));
        }
    }

    @Test
    public void deleteJournalistPositiveTest() throws Exception {
        try {
            journalistService.deleteJournalistById(null);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().equals("id must not be empty"));
        }

        try {
            journalistService.deleteJournalistById(0);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().equals("id must be greater than zero"));
        }

        assertTrue(journalistService.deleteJournalistById(990) == 0);
    }

}
