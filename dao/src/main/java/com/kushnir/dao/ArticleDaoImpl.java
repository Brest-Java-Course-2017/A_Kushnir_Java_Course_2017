package com.kushnir.dao;

import com.kushnir.model.Article;
import org.springframework.dao.DataAccessException;

import java.time.LocalDate;
import java.util.List;

/**
 * Article DAO implementation
 */
public class ArticleDaoImpl implements ArticleDao {
    @Override
    public List<Article> getAllArticles(LocalDate createDateStart, LocalDate createDateEnd) throws DataAccessException {
        return null;
    }

    @Override
    public Article getArticleById(Integer id) throws DataAccessException {
        return null;
    }

    @Override
    public Integer addArticle(Article article) throws DataAccessException {
        return null;
    }

    @Override
    public Integer updateArticle(Article article) throws DataAccessException {
        return null;
    }

    @Override
    public Integer deleteArticle(Integer id) throws DataAccessException {
        return null;
    }
}
