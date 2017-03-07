package com.kushnir.dao;

import java.time.LocalDate;
import java.util.Objects;

/**
 * model of Journalist
 */
public class Journalist {

    private Integer     id;
    private String      name;
    private Integer     rate;
    private LocalDate   birthDate;

    public Journalist() {
    }

    public Journalist(String name, Integer rate, LocalDate birthDate) {
        this.name = name;
        this.rate = rate;
        this.birthDate = birthDate;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getRate() {
        return rate;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    /*
    * This method set birthDate from string in format: "YYYY-MM-DD"
    */
    public void setBirthDateFromString(String birthDate) {
        this.birthDate = LocalDate.parse(birthDate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Journalist)) return false;
        Journalist that = (Journalist) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getRate(), that.getRate()) &&
                Objects.equals(getBirthDate(), that.getBirthDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getRate(), getBirthDate());
    }

    @Override
    public String toString() {
        return "Journalist{" +
                "id = " + id +
                ", name = '" + name + '\'' +
                ", rate = " + rate +
                ", birthDate = " + birthDate +
                '}';
    }
}
