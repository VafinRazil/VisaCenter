package com.rvafin.visacenter.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Entity
@Table(name = "tourist")
public class TouristEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String firstname;

    private String surname;

    private LocalDate birthday;

    @Column(name = "international_passport_num")
    private int internPassNum;

    @Column(name = "international_passport_series")
    private String internPassSeries;

    private String patronymic;

    private String email;

    @ManyToOne
    private CountryEntity country;

    @Column(name = "expiration_date_passport")
    private LocalDate expirationDatePass;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tourist")
    private List<EVisaEntity> eVisas = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tourist")
    private List<VisaApplicationFormEntity> visaApplicationFormEntities = new ArrayList<>();

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

    public int getInternPassNum() {
        return internPassNum;
    }

    public void setInternPassNum(int internPassNum) {
        this.internPassNum = internPassNum;
    }

    public String getInternPassSeries() {
        return internPassSeries;
    }

    public void setInternPassSeries(String internPassSeries) {
        this.internPassSeries = internPassSeries;
    }

    public List<EVisaEntity> getEVisas() {
        return eVisas;
    }

    public void setEVisas(List<EVisaEntity> eVisas) {
        this.eVisas = eVisas;
    }

    public List<VisaApplicationFormEntity> getVisaApplicationFormEntities() {
        return visaApplicationFormEntities;
    }

    public void setVisaApplicationFormEntities(List<VisaApplicationFormEntity> visaApplicationFormEntities) {
        this.visaApplicationFormEntities = visaApplicationFormEntities;
    }

    public String getFullName(){
        return String.format("%s %s %s", surname, firstname, patronymic).toUpperCase(Locale.ENGLISH);
    }
}
