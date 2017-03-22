package com.kushnir.service;

import com.kushnir.dao.JournalistDao;
import com.kushnir.model.Journalist;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;

/**
 * Journalist Service implementation
 */
@Service
@Transactional
public class JournalistServiceImpl implements JournalistService {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    JournalistDao journalistDao;

    public JournalistServiceImpl (JournalistDao journalistDao) {
        this.journalistDao = journalistDao;
    }

    @Override
    public List<Journalist> getAllJournalists(LocalDate birthDateStart, LocalDate birthDateEnd) {
        return null;
    }

    @Override
    public Journalist getJournalistById(Integer id) {
        return null;
    }

    @Override
    public Journalist getJournalistByName(String name) {
        return null;
    }

    @Override
    public Integer addJournalist(Journalist journalist) {
        return null;
    }

    @Override
    public Integer updateJournalist(Journalist journalist) {
        return null;
    }

    @Override
    public Integer deleteJournalist(Integer id) {
        return null;
    }
}
