package com.rvafin.visacenter.repository;

import com.rvafin.visacenter.entity.CountryEntity;
import com.rvafin.visacenter.entity.TouristEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface TouristEntityRepository extends JpaRepository<TouristEntity, Long> {

    @Query("SELECT TouristEntity FROM TouristEntity t " +
            "LEFT JOIN VisaApplicationFormEntity v ON t.id=v.id " +
            "WHERE v.status = 'SEND'")
    List<TouristEntity> findTouristEntitiesWithVisaInStatusSend();

    List<TouristEntity> findTouristEntitiesByInternPassNumAndInternPassSeriesAndCountryId(
            int internPassNum,
            String internPassSeries,
            Long countryId);
}
