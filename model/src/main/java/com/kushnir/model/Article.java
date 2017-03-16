package com.kushnir.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * model of Article
 */
public class Article {

    private Integer     id;
    private String      name;
    private LocalDate   dateCreate;
    private Integer     popularity;
    private Integer     journalistId;
    private String      journalilstName;

    public Article() {
    }

    public Article(String name, LocalDate dateCreate, Integer popularity, Integer journalistId) {
        this.name = name;
        this.dateCreate = dateCreate;
        this.popularity = popularity;
        this.journalistId = journalistId;
    }

    public Article(Integer id, String name, LocalDate dateCreate, Integer popularity, Integer journalistId) {
        this.id = id;
        this.name = name;
        this.dateCreate = dateCreate;
        this.popularity = popularity;
        this.journalistId = journalistId;
    }

    public Article(Integer id, String name, LocalDate dateCreate, Integer popularity, Integer journalistId, String journalilstName) {
        this.id = id;
        this.name = name;
        this.dateCreate = dateCreate;
        this.popularity = popularity;
        this.journalistId = journalistId;
        this.journalilstName = journalilstName;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDateCreate() {
        return dateCreate;
    }

    public Integer getPopularity() {
        return popularity;
    }

    public Integer getJournalistId() {
        return journalistId;
    }

    public String getJournalilstName() {
        return journalilstName;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDateCreate(LocalDate dateCreate) {
        this.dateCreate = dateCreate;
    }

    /*
    * This method set dateCreate from string in format: "YYYY-MM-DD"
    */
    public void setDateCreateFromString(String dateCreate) {
        this.dateCreate = LocalDate.parse(dateCreate);
    }

    public void setPopularity(Integer popularity) {
        this.popularity = popularity;
    }

    public void setJournalistId(Integer journalistId) {
        this.journalistId = journalistId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Article)) return false;
        Article article = (Article) o;
        return Objects.equals(getName(), article.getName()) &&
                Objects.equals(getDateCreate(), article.getDateCreate()) &&
                Objects.equals(getPopularity(), article.getPopularity()) &&
                Objects.equals(getJournalistId(), article.getJournalistId());
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dateCreate=" + dateCreate +
                ", popularity=" + popularity +
                ", journalistId=" + journalistId +
                ", journalilstName='" + journalilstName + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getDateCreate(), getPopularity(), getJournalistId());
    }


}