package com.rvafin.visacenter.dto.request;

import java.time.LocalDate;

public class VisaApplicationRequestDTO {

    private Long touristId;

    private String profession;


    private Long travelCountryId;

    private String familyStatus;

    private String zayaId;

    public VisaApplicationRequestDTO(){}

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

    public Long getTravelCountryId() {
        return travelCountryId;
    }

    public void setTravelCountryId(Long travelCountryId) {
        this.travelCountryId = travelCountryId;
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

    @Override
    public String toString() {
        return "visa application\n{" +
                "\ntouristId = " + touristId +
                "\n, profession = '" + profession + '\'' +
                "\n, travelCountryId ="  + travelCountryId +
                "\n, familyStatus = '" + familyStatus + '\'' +
                "\n, zayaId = '" + zayaId + '\'' +
                "\n}";
    }
}
