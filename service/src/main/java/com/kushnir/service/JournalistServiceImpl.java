package com.kushnir.service;

import com.kushnir.model.Journalist;

import java.time.LocalDate;
import java.util.List;

/**
 * Journalist Service implementation
 */
public class JournalistServiceImpl implements JournalistService {
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
