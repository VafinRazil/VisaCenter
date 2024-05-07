package com.rvafin.visacenter.entity;

import com.rvafin.visacenter.enums.VisaStatus;
import com.rvafin.visacenter.imprecise_data_matcher.annotations.FieldParams;
import com.rvafin.visacenter.imprecise_data_matcher.interfaces.StringFormatter;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Entity
@Table(name = "tourist")
public class TouristEntity implements StringFormatter {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @FieldParams(position = 3)
    private String firstname;

    @FieldParams(position = 4)
    private String surname;

    private String patronymic;

    @FieldParams(position = 2, format = "dd.MM.yyyy")
    private LocalDate birthday;

    @FieldParams(position = 0)
    @Column(name = "international_passport_num")
    private int internPassNum;

    @FieldParams(position = 1)
    @Column(name = "international_passport_series")
    private String internPassSeries;

    private String email;

    @ManyToOne
    private CountryEntity country;

    @Column(name = "expiration_date_passport")
    private LocalDate expirationDatePass;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tourist")
    private List<EVisaEntity> eVisas = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tourist")//мб нужно будет указать fetchType Eager
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

    public void addEVisa(EVisaEntity eVisa) {
        this.eVisas.add(eVisa);
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

    public Optional<VisaApplicationFormEntity> getSentVisaApplicationByCountryId(Long countryId){
        return getVisaApplicationFormEntities()
                .stream()
                .filter(visaApplicationForm ->
                        visaApplicationForm.getTravelCountry().getId().compareTo(countryId) == 0
                                && visaApplicationForm.getStatus().equals(VisaStatus.SEND))
                .findAny();
    }
}
