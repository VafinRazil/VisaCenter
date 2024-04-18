package com.rvafin.visacenter.entity;

import com.rvafin.visacenter.enums.VisaStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "visa_application_form")
public class VisaApplicationFormEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private TouristEntity tourist;

    private String profession;

    @Column(name = "intern_pass_issue_date")
    private LocalDate passportIssueDate;

    @ManyToOne
    private CountryEntity travelCountry;

    @Column(name = "family_status")
    private String familyStatus;

    @Column(name = "zaya_id")
    private String zayaId;

    @Enumerated(EnumType.STRING)
    private VisaStatus status;

    @Column(name = "created_on")
    @CreationTimestamp
    private LocalDateTime createdOn;

    @Column(name = "updated_on")
    @UpdateTimestamp
    private LocalDateTime updatedOn;

    @ManyToOne
    private UserEntity creator;

    public VisaApplicationFormEntity() {}

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public UserEntity getCreator() {
        return creator;
    }

    public void setCreator(UserEntity creator) {
        this.creator = creator;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public TouristEntity getTourist() {
        return tourist;
    }

    public void setTourist(TouristEntity tourist) {
        this.tourist = tourist;
    }

    public CountryEntity getTravelCountry() {
        return travelCountry;
    }

    public void setTravelCountry(CountryEntity travelCountry) {
        this.travelCountry = travelCountry;
    }

    public LocalDate getPassportIssueDate() {
        return passportIssueDate;
    }

    public void setPassportIssueDate(LocalDate passportIssueDate) {
        this.passportIssueDate = passportIssueDate;
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

    public LocalDateTime getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(LocalDateTime updatedOn) {
        this.updatedOn = updatedOn;
    }
}
