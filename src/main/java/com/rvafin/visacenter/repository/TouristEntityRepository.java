package com.rvafin.visacenter.repository;

import com.rvafin.visacenter.entity.TouristEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TouristEntityRepository extends JpaRepository<TouristEntity, Long> {

    @Query("SELECT t FROM TouristEntity t " +
            "LEFT JOIN VisaApplicationFormEntity v ON t.id=v.tourist.id " +
            "WHERE v.status = 'SEND'")
    List<TouristEntity> findTouristEntitiesWithVisaInStatusSend();

    List<TouristEntity> findTouristEntitiesByInternPassNumAndInternPassSeries(
            int internPassNum,
            String internPassSeries);
}
