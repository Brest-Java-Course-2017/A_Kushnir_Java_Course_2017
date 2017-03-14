package com.kushnir.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.kushnir.model.Article;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Article DAO implementation
 */
public class ArticleDaoImpl implements ArticleDao {

    private static final Logger LOGGER = LogManager.getLogger();

    static final String ID_FIELDNAME = "id_article";
    static final String NAIM_FIELDNAME = "naim";
    static final String CREATEDATE_FIELDNAME = "create_date";
    static final String POPULARITY_FIELDNAME = "popularity";
    static final String IDJOURNALIST_FIELDNAME = "id_journalist";
    static final String NAMEJOURNALIST_FIELDNAME = "journalistName";

    static final String ID_PARAMETERNAME = "p_id_a";
    static final String NAIM_PARAMETERNAME = "p_naim";
    static final String CREATEDATE_PARAMETERNAME = "p_create_date";
    static final String POPULARITY_PARAMETERNAME = "p_popularity";
    static final String IDJOURNALIST_PARAMETERNAME = "p_id_journalist";

    static final String CREATEDATE_START_PARAMETERNAME = "p_createDateStart";
    static final String CREATEDATE_END_PARAMETERNAME = "p_createDateEnd";

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public ArticleDaoImpl(DataSource dataSource) {
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Value("${article.selectAll}")
    String getAllArticlesSqlquery;

    @Value("${article.selectByID}")
    String getArticleByIdsSqlquery;

    @Value("${article.selectByNaim}")
    String getArticleByNaimsSqlquery;

    @Value("${article.insert}")
    String addArticleSqlquery;

    @Value("${article.update}")
    String updateArticleSqlquery;

    @Value("${article.delete}")
    String deleteArticleSqlquery;

    @Override
    public List<Article> getAllArticles(LocalDate createDateStart, LocalDate createDateEnd) throws DataAccessException {
        LOGGER.debug("getAllArticles(createDateStart: " + createDateStart + ", createDateEnd: " + createDateEnd);
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue(CREATEDATE_START_PARAMETERNAME,createDateStart);
        parameterSource.addValue(CREATEDATE_END_PARAMETERNAME,createDateEnd);
        List<Article> articleList = namedParameterJdbcTemplate.query(
                getAllArticlesSqlquery
                ,parameterSource
                ,new ArticleRowMapper());
        return articleList;
    }

    @Override
    public Article.ArticleDisplayPage getDataArticleDisplayPage(LocalDate createDateStart, LocalDate createDateEnd) {
        LOGGER.debug("getDataArticleDisplayPage(createDateStart: " + createDateStart + ", createDateEnd: " + createDateEnd);
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue(CREATEDATE_START_PARAMETERNAME,createDateStart);
        parameterSource.addValue(CREATEDATE_END_PARAMETERNAME,createDateEnd);
        Article.ArticleDisplayPage articleDisplayPage = namedParameterJdbcTemplate.queryForObject(
                getAllArticlesSqlquery
                ,parameterSource
                ,new ArticleDisplayPageRowMapper());
        return articleDisplayPage;
    }

    @Override
    public Article getArticleById(Integer id) throws DataAccessException {
        LOGGER.debug("getArticleById(id: " + id);
        MapSqlParameterSource parameterSource = new MapSqlParameterSource(ID_PARAMETERNAME, id);
        Article article = namedParameterJdbcTemplate.queryForObject(
                getArticleByIdsSqlquery
                ,parameterSource
                ,new ArticleRowMapper());
        return article;
    }

    @Override
    public Article getArticleByNaim(String naim) throws DataAccessException {
        LOGGER.debug("getArticleByNaim(naim: " + naim);
        MapSqlParameterSource parameterSource = new MapSqlParameterSource(NAIM_PARAMETERNAME, naim);
        Article article = namedParameterJdbcTemplate.queryForObject(
                getArticleByNaimsSqlquery
                ,parameterSource
                ,new ArticleRowMapper());
        return article;
    }

    @Override
    public Integer addArticle(Article article) throws DataAccessException {
        LOGGER.debug("addArticle(article: " + article);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue(NAIM_PARAMETERNAME, article.getName());
        parameterSource.addValue(CREATEDATE_PARAMETERNAME, article.getDateCreate());
        parameterSource.addValue(POPULARITY_PARAMETERNAME, article.getPopularity());
        parameterSource.addValue(IDJOURNALIST_PARAMETERNAME, article.getJournalistId());
        namedParameterJdbcTemplate.update(addArticleSqlquery, parameterSource, keyHolder);
        return keyHolder.getKey().intValue();
    }

    @Override
    public Integer updateArticle(Article article) throws DataAccessException {
        LOGGER.debug("updateArticle(article: " + article);
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue(ID_PARAMETERNAME, article.getId());
        parameterSource.addValue(NAIM_PARAMETERNAME, article.getName());
        parameterSource.addValue(CREATEDATE_PARAMETERNAME, article.getDateCreate());
        parameterSource.addValue(POPULARITY_PARAMETERNAME, article.getPopularity());
        parameterSource.addValue(IDJOURNALIST_PARAMETERNAME, article.getJournalistId());
        return namedParameterJdbcTemplate.update(updateArticleSqlquery, parameterSource);
    }

    @Override
    public Integer deleteArticle(Integer id) throws DataAccessException {
        LOGGER.debug("deleteArticle(id: " + id);
        MapSqlParameterSource parameterSource = new MapSqlParameterSource(ID_PARAMETERNAME, id);
        return namedParameterJdbcTemplate.update(deleteArticleSqlquery, parameterSource);
    }


    private class ArticleRowMapper implements RowMapper<Article> {

        @Override
        public Article mapRow(ResultSet resultSet, int i) throws SQLException {
            Article article = new Article(
                    resultSet.getInt(ID_FIELDNAME)
                    ,resultSet.getString(NAIM_FIELDNAME)
                    ,resultSet.getDate(CREATEDATE_FIELDNAME).toLocalDate()
                    ,resultSet.getInt(POPULARITY_FIELDNAME)
                    ,resultSet.getInt(IDJOURNALIST_FIELDNAME)
                    ,resultSet.getString(NAMEJOURNALIST_FIELDNAME));
            return article;
        }
    }

    private class ArticleDisplayPageRowMapper implements RowMapper<Article.ArticleDisplayPage> {

        @Override
        public Article.ArticleDisplayPage mapRow(ResultSet resultSet, int i) throws SQLException {
            List<Article> ArticleList = new ArrayList<>();
            Map<String, Integer> aggregativeValues = new HashMap<>();
            int count = 0;
            int sumPopularity = 0;

            while(resultSet.next()) {
                ArticleList.add(new Article(
                        resultSet.getInt(ID_FIELDNAME)
                        ,resultSet.getString(NAIM_FIELDNAME)
                        ,resultSet.getDate(CREATEDATE_FIELDNAME).toLocalDate()
                        ,resultSet.getInt(POPULARITY_FIELDNAME)
                        ,resultSet.getInt(IDJOURNALIST_FIELDNAME)
                        ,resultSet.getString(NAMEJOURNALIST_FIELDNAME)));

                count ++;
                sumPopularity += resultSet.getInt(POPULARITY_FIELDNAME);
            }

            aggregativeValues.put("fieldsCount", count);
            aggregativeValues.put("sumPopularity", sumPopularity);
            aggregativeValues.put("avgPopularity", sumPopularity/count);

            return new Article.ArticleDisplayPage(ArticleList, aggregativeValues);
        }
    }

}
