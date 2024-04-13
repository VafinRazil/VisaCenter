package com.rvafin.visacenter.dto.request;

import java.time.LocalDate;

public class CreateTouristRequestDTO {

    private String firstname;

    private String surname;

    private String patronymic;

    private LocalDate birthday;

    private String email;

    private Long country;

    private LocalDate expirationDatePass;

    private String internPassNum;

    private int internPassSeries;

    public CreateTouristRequestDTO(){}

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getCountry() {
        return country;
    }

    public void setCountry(Long country) {
        this.country = country;
    }

    public LocalDate getExpirationDatePass() {
        return expirationDatePass;
    }

    public void setExpirationDatePass(LocalDate expirationDatePass) {
        this.expirationDatePass = expirationDatePass;
    }

    public String getInternPassNum() {
        return internPassNum;
    }

    public void setInternPassNum(String internPassNum) {
        this.internPassNum = internPassNum;
    }

    public int getInternPassSeries() {
        return internPassSeries;
    }

    public void setInternPassSeries(int internPassSeries) {
        this.internPassSeries = internPassSeries;
    }
}
