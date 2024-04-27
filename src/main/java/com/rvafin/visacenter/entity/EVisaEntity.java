package com.rvafin.visacenter.entity;

import com.rvafin.visacenter.imprecise_data_matcher.annotations.FieldParams;
import com.rvafin.visacenter.imprecise_data_matcher.interfaces.StringFormatter;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "evisa")
public class EVisaEntity implements StringFormatter {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @FieldParams(position = 3)
    private String firstname;

    @FieldParams(position = 4)
    private String surname;

    @FieldParams(position = 2, format = "dd.MM.yyyy")
    private LocalDate birthday;

    @FieldParams(position = 0)
    @Column(name = "passport_num")
    private int passNum;

    @FieldParams(position = 1)
    @Column(name = "passport_series")
    private String passSeries;

    @Column(name = "issue_date")
    private LocalDate issueDate;

    private String number;

    @Column(name = "expire_date")
    private LocalDate expireDate;

    @ManyToOne
    private CountryEntity country;

    @ManyToOne(fetch = FetchType.LAZY)
    private TouristEntity tourist;

    @Column(name = "zaya_id")
    private String zayaId;

    @Column(name = "created_on")
    @CreationTimestamp
    private LocalDateTime createdOn;

    public EVisaEntity(){}

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public CountryEntity getCountry() {
        return country;
    }

    public void setCountry(CountryEntity country) {
        this.country = country;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public int getPassNum() {
        return passNum;
    }

    public void setPassNum(int passNum) {
        this.passNum = passNum;
    }

    public String getPassSeries() {
        return passSeries;
    }

    public void setPassSeries(String passSeries) {
        this.passSeries = passSeries;
    }

    public LocalDate getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(LocalDate expireDate) {
        this.expireDate = expireDate;
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

    public String getZayaId() {
        return zayaId;
    }

    public void setZayaId(String zayaId) {
        this.zayaId = zayaId;
    }

    public TouristEntity getTourist() {
        return tourist;
    }

    public void setTourist(TouristEntity tourist) {
        this.tourist = tourist;
    }

    public String getFullName(){
        return String.format("%s %s", surname, firstname).toUpperCase(Locale.ENGLISH);
    }
}
