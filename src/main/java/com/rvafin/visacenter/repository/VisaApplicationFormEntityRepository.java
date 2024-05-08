package com.rvafin.visacenter.repository;

import com.rvafin.visacenter.entity.VisaApplicationFormEntity;
import com.rvafin.visacenter.enums.VisaStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface VisaApplicationFormEntityRepository extends JpaRepository<VisaApplicationFormEntity, Long> {

    List<VisaApplicationFormEntity> findVisaApplicationFormEntitiesByTravelCountryIdAndCreatedOnBetweenAndStatusOrderByCreatedOn(
            Long travelCountryId,
            LocalDateTime startDate,
            LocalDateTime endDate,
            VisaStatus status);

    List<VisaApplicationFormEntity> findVisaApplicationFormEntitiesByStatus(VisaStatus status);
}
