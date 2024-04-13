package com.rvafin.visacenter.dto.request;

import com.rvafin.visacenter.entity.CountryEntity;

import java.time.LocalDate;

public class CreateVisaApplicationRequestDTO {

    private Long id;

    private Long touristId;

    private String profession;

    private LocalDate passportIssueDate;

    private CountryEntity travelCountry;

    private String familyStatus;

    private String zayaId;

    public CreateVisaApplicationRequestDTO(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTouristId() {
        return touristId;
    }

    public void setTouristId(Long touristId) {
        this.touristId = touristId;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public LocalDate getPassportIssueDate() {
        return passportIssueDate;
    }

    public void setPassportIssueDate(LocalDate passportIssueDate) {
        this.passportIssueDate = passportIssueDate;
    }

    public CountryEntity getTravelCountry() {
        return travelCountry;
    }

    public void setTravelCountry(CountryEntity travelCountry) {
        this.travelCountry = travelCountry;
    }

    public String getFamilyStatus() {
        return familyStatus;
    }

    public void setFamilyStatus(String familyStatus) {
        this.familyStatus = familyStatus;
    }

    public String getZayaId() {
        return zayaId;
    }

    public void setZayaId(String zayaId) {
        this.zayaId = zayaId;
    }
}
