package com.rvafin.visacenter.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.rvafin.visacenter.entity.CountryEntity;
import com.rvafin.visacenter.enums.VisaStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class VisaApplicationResponseDTO {

    private Long id;

    private String fullName;

    private LocalDate passportIssueDate;

    private LocalDate birthday;

    private CountryEntity travelCountry;

    @JsonProperty("zaya_id")
    private String zayaId;

    private VisaStatus status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime createdOn;

    public VisaApplicationResponseDTO(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getPassportIssueDate() {
        return passportIssueDate;
    }

    public void setPassportIssueDate(LocalDate passportIssueDate) {
        this.passportIssueDate = passportIssueDate;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public CountryEntity getTravelCountry() {
        return travelCountry;
    }

    public void setTravelCountry(CountryEntity travelCountry) {
        this.travelCountry = travelCountry;
    }

    public String getZayaId() {
        return zayaId;
    }

    public void setZayaId(String zayaId) {
        this.zayaId = zayaId;
    }

    public VisaStatus getStatus() {
        return status;
    }

    public void setStatus(VisaStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }
}
