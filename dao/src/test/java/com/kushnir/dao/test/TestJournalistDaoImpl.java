package com.kushnir.dao.test;

import com.kushnir.dao.JournalistDao;
import com.kushnir.model.Journalist;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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

    //private static final Journalist journalist = new Journalist("Test", 10, LocalDate.parse("1987-10-10"));

    @Autowired
    JournalistDao journalistDao;

    @Test
    public void getAllJournalistsTest() throws Exception {
        List<Journalist> journalists = journalistDao.getAllJournalists(null, null);
        assertTrue(journalists.size() > 0);
        System.out.print(journalists);
    }

    //TODO implement getJournalistById() test
    //TODO implement addJournalist() test
    //TODO implement updateJournalist() test
    //TODO implement deleteJournalist() test
}
