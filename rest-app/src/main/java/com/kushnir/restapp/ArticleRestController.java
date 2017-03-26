package com.kushnir.restapp;

import com.kushnir.model.Article;
import com.kushnir.service.ArticleService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.util.List;

/**
 * Article rest controller
 */
@CrossOrigin
@RestController
public class ArticleRestController {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    ArticleService articleService;

    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler({IllegalArgumentException.class})
    public String incorrectDataError() {
        return "{  \"response\" : \"Incorrect Data Error\" }";
    }

    //curl -X GET -v localhost:8088/articles?createDateStart=...&createDateEnd=...
    @RequestMapping(value = "/articles", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public List<Article> getArticles(
            @RequestParam(value = "createDateStart", required = false)
                @DateTimeFormat(pattern = "yyyy.MM.dd") LocalDate createDateStart,
            @RequestParam(value = "createDateEnd", required = false)
                @DateTimeFormat(pattern = "yyyy.MM.dd") LocalDate createDateEnd) {
        LOGGER.debug("getArticles(createDateStart: {}, createDateEnd: {})", createDateStart , createDateEnd);

        return articleService.getAllArticles(createDateStart, createDateEnd);
    }

    //curl -X GET -v localhost:8088/articles/article/1
    @RequestMapping(value = "/articles/article/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.FOUND)
    @ResponseBody
    public Article getArticleById(
            @PathVariable(value = "id") Integer id){
        LOGGER.debug("getArticleById(id: {})", id);
        return articleService.getArticleById(id);
    }

    //curl -X GET -v localhost:8088/articles/article?naim=...
    @RequestMapping(value = "/articles/article/naim", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.FOUND)
    @ResponseBody
    public Article getArticleByNaim(
            @RequestParam(value = "naim") String naim){
        LOGGER.debug("getArticleByNaim(naim: {})", naim);
        return articleService.getArticleByNaim(naim);
    }

    /*
    * curl -H "Content-Type: application/json" -X POST -d '{"name":"","createDate":"","Popularity":"","JournalistId":""}'
    * -v localhost:8088/articles/article
    */
    @RequestMapping(value = "/articles/article", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Integer addArticle (
            @RequestBody Article article) {
        LOGGER.debug("addArticle(article: {})", article);
        return  articleService.addArticle(article);
    }

    /*
    * curl -H "Content-Type: application/json" -X PUT -d '{"name":"...","createDate":"...","Popularity":"...", "JournalistId":"..."}'
    * -v localhost:8088/articles/article
    */
    @RequestMapping(value = "/articles/article", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    @ResponseBody
    public Integer updateArticle (
            @RequestBody Article article) {
        LOGGER.debug("updateArticle(article: {})", article);
        return  articleService.updateArticle(article);
    }

    //curl -X DELETE -v localhost:8088/articles/article/1
    @RequestMapping(value = "/articles/article/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public Integer deleteArticle (
            @PathVariable(value = "id") Integer id) {
        LOGGER.debug("deleteArticle(id: {})", id);
        return articleService.deleteArticle(id);
    }

}
