package com.kushnir.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
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

    public Journalist(Integer id, String name, Integer rate, LocalDate birthDate) {
        this.id = id;
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
                "id=" + id +
                ", name='" + name + '\'' +
                ", rate=" + rate +
                ", birthDate=" + birthDate +
                '}';
    }

    // container for Journalists Page print
    public static class JournalistViewPage {
        private List<Journalist> journalists;
        private Map<String, Integer> aggregativeParam;

        public JournalistViewPage() {
        }

        public JournalistViewPage(List<Journalist> journalists, Map<String, Integer> aggregativeParam) {
            this.journalists = journalists;
            this.aggregativeParam = aggregativeParam;
        }

        public List<Journalist> getJournalists() {
            return journalists;
        }

        public Map<String, Integer> getAggregativeParam() {
            return aggregativeParam;
        }

        public void setJournalists(List<Journalist> journalists) {
            this.journalists = journalists;
        }

        public void setAggregativeParam(Map<String, Integer> aggregativeParam) {
            this.aggregativeParam = aggregativeParam;
        }

        @Override
        public String toString() {
            return "JournalistViewPage{" +
                    "journalists=" + journalists +
                    ", aggregativeParam=" + aggregativeParam +
                    '}';
        }
    }
}
