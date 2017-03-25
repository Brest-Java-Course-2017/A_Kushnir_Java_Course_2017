package com.kushnir.service;

import com.kushnir.dao.ArticleDao;
import com.kushnir.dao.JournalistDao;
import com.kushnir.model.Journalist;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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

    @Autowired
    ArticleDao articleDao;

    @Override
    public List<Journalist> getAllJournalists(LocalDate birthDateStart, LocalDate birthDateEnd)
            throws IllegalArgumentException {
        LOGGER.debug("getAllJournalists(birthDateStart: "+birthDateStart+", birthDateEnd: "+birthDateEnd+")");
        if ((birthDateStart != null) && (birthDateEnd != null)) {
            Assert.isTrue(birthDateStart.isBefore(birthDateEnd), "birthDateStart must be before birthDateEnd");
        }
        return journalistDao.getAllJournalists(birthDateStart, birthDateEnd);
    }

    @Override
    public Journalist getJournalistById(Integer id) throws IllegalArgumentException {
        LOGGER.debug("getJournalistById(id: "+id+")");
        Assert.notNull(id,"The id must not be empty");
        Assert.isTrue(id > 0, "The id must be greater than zero");
        try {
            return journalistDao.getJournalistById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new IllegalArgumentException(
                    "Journalist by id("+id+") " +
                            " does not exist in the database");
        }
    }

    @Override
    public Journalist getJournalistByName(String name) throws IllegalArgumentException {
        LOGGER.debug("getJournalistByName(name: "+name+")");
        Assert.notNull(name,"The name not be empty");
        Assert.hasText(name, "The name must contain text");
        try {
            return journalistDao.getJournalistByName(name);
        } catch (EmptyResultDataAccessException e) {
            throw new IllegalArgumentException(
                    "Journalist by name("+name+") " +
                            " does not exist in the database");
        }
    }

    @Override
    public Integer addJournalist(Journalist journalist) throws IllegalArgumentException {
        LOGGER.debug("addJournalist(journalist: {})", journalist);
        Assert.notNull(journalist, "journalist must not be empty");
        Assert.isNull(journalist.getId(), "journalist id must be empty");
        Assert.notNull(journalist.getName(), "The name must not be empty");
        Assert.hasText(journalist.getName(), "The name must contain text");
        try {
            Assert.isNull(journalistDao.getJournalistByName(journalist.getName()),
                    "journalist by name (" + journalist.getName() + ") already exist in the database");
        } catch (EmptyResultDataAccessException e) {/*OK, Journalist by name does not exist*/}

        Assert.notNull(journalist.getBirthDate(), "The birthDate must not be empty");
        Assert.isTrue(journalist.getBirthDate().isBefore(LocalDate.now()), "The birthDate must be before today");
        Assert.notNull(journalist.getRate(), "The rating must not be empty");

        return journalistDao.addJournalist(journalist);
    }

    @Override
    public Integer updateJournalist(Journalist journalist) throws IllegalArgumentException {
        LOGGER.debug("updateJournalist(journalist: {})", journalist);
        Assert.notNull(journalist, "journalist must not be empty");
        Assert.notNull(journalist.getId(), "journalist id must not be empty");
        Assert.isTrue(journalist.getId() > 0, "journalist id must be greater than zero");
        try {
            Assert.notNull(journalistDao.getJournalistById(journalist.getId()),
                    "journalist by id("+journalist.getId()+") " +
                            " does not exist in the database");
        } catch (EmptyResultDataAccessException e) {
            throw new IllegalArgumentException("journalist by id("+journalist.getId()+") " +
                    " does not exist in the database");
        }

        Assert.notNull(journalist.getName(), "The name must not be empty");
        Assert.hasText(journalist.getName(), "The name must contain text");
        Assert.notNull(journalist.getBirthDate(), "The birthDate must not be empty");
        Assert.isTrue(journalist.getBirthDate().isBefore(LocalDate.now())
                , "The birthDate must be before today");
        Assert.notNull(journalist.getRate(), "The rating must not be empty");

        return journalistDao.updateJournalist(journalist);
    }

    @Override
    @Transactional
    public Integer deleteJournalistById(Integer id) throws IllegalArgumentException {
        LOGGER.debug("deleteJournalistById(id: {})", id);
        Assert.notNull(id, "id must not be empty");
        Assert.isTrue(id > 0, "id must be greater than zero");

        articleDao.deleteArticleByJournalistID(id);

        return journalistDao.deleteJournalist(id);
    }
}
