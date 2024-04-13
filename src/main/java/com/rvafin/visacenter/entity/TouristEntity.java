package com.rvafin.visacenter.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tourist")
public class TouristEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstname;

    private String surname;

    private String patronymic;

    private LocalDate birthday;

    private String email;

    @ManyToOne
    private CountryEntity country;

    @Column(name = "expiration_date_passport")
    private LocalDate expirationDatePass;

    @Column(name = "international_passport_num")
    private String internPassNum;

    @Column(name = "international_passport_series")
    private int internPassSeries;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tourist")
    private List<EVisaEntity> eVisa = new ArrayList<>();

    public TouristEntity(){}

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

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

    public CountryEntity getCountry() {
        return country;
    }

    public void setCountry(CountryEntity country) {
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

    public List<EVisaEntity> geteVisa() {
        return eVisa;
    }

    public void seteVisa(List<EVisaEntity> eVisa) {
        this.eVisa = eVisa;
    }
}
