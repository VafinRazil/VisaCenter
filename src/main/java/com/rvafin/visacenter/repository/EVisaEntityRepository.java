package com.rvafin.visacenter.repository;

import com.rvafin.visacenter.entity.EVisaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface EVisaEntityRepository extends JpaRepository<EVisaEntity, Long> {

    /**
     * Получаем визы для определенной страны, дата формирования {@link EVisaEntity#getIssueDate} которых между указанными в параметрах метода
     *
     * @return список виз {@link EVisaEntity}, которые  отсортированы по дате формирования
     * */
    List<EVisaEntity> getEVisaEntitiesByCountryIdAndIssueDateBetweenOrderByIssueDate(Long countryId, LocalDate startIssueDate, LocalDate endIssueDate);


}
