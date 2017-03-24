package com.kushnir.service.test.junit;

import com.kushnir.service.JournalistService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Junit Negative Test of Journalist Service implementation
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-service-test-config.xml"})
@Transactional
public class JournalistServiceImplNegativeTestJunit {

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


    @Test
    public void getAllJournalistsNegativeTest() throws Exception {

    }

    @Test
    public void getJournalistByIdNegativeTest() throws Exception {

    }

    @Test
    public void getJournalistByNameNegativeTest() throws Exception {

    }

    @Test
    public void addJournalistNegativeTest() throws Exception {

    }

    @Test
    public void updateJournalistNegativeTest() throws Exception {

    }

    @Test
    public void deleteJournalistPositiveTest() throws Exception {

    }

}
