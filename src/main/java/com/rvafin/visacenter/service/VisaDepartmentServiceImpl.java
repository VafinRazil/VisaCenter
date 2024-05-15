package com.rvafin.visacenter.service;

import com.rvafin.visacenter.dto.request.VisaApplicationRequestDTO;
import com.rvafin.visacenter.dto.response.VisaApplicationResponseDTO;
import com.rvafin.visacenter.dto.response.VisaResponseDTO;
import com.rvafin.visacenter.entity.EVisaEntity;
import com.rvafin.visacenter.entity.VisaApplicationFormEntity;
import com.rvafin.visacenter.enums.VisaStatus;
import com.rvafin.visacenter.mapper.EVisaMapper;
import com.rvafin.visacenter.mapper.VisaApplicationFormMapper;
import com.rvafin.visacenter.repository.*;
import com.rvafin.visacenter.service.interfaces.DataMatcherService;
import com.rvafin.visacenter.service.interfaces.VisaDepartmentService;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.AccessDeniedException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class VisaDepartmentServiceImpl implements VisaDepartmentService {

    private static Logger log = LoggerFactory.getLogger(VisaDepartmentServiceImpl.class);

    private static final Set<VisaStatus> allowedStatuses = Set.of(VisaStatus.CREATE, VisaStatus.REVIEWED);

    private final VisaApplicationFormEntityRepository visaApplicationFormEntityRepository;
    private final EVisaEntityRepository eVisaEntityRepository;
    private final UserEntityRepository userEntityRepository;
    private final VisaApplicationFormMapper visaApplicationFormMapper;
    private final EVisaMapper eVisaMapper = Mappers.getMapper(EVisaMapper.class);
    private final DataMatcherService dataMatcherService;

    @Autowired
    public VisaDepartmentServiceImpl(
            VisaApplicationFormEntityRepository visaApplicationFormEntityRepository,
            EVisaEntityRepository eVisaEntityRepository,
            UserEntityRepository userEntityRepository,
            VisaApplicationFormMapper visaApplicationFormMapper,
            DataMatcherService dataMatcherService
    ){
        this.eVisaEntityRepository = eVisaEntityRepository;
        this.visaApplicationFormEntityRepository = visaApplicationFormEntityRepository;
        this.userEntityRepository = userEntityRepository;
        this.visaApplicationFormMapper = visaApplicationFormMapper;
        this.dataMatcherService = dataMatcherService;
    }

    @Transactional
    @Override
    //возможно не будет работать, нужно через touristRepo сохранять
    public boolean createVisaApplication(VisaApplicationRequestDTO visaApplicationRequestDTO) {
        log.info("Create new {}", visaApplicationRequestDTO);
        VisaApplicationFormEntity newVisaApplicationEntity = visaApplicationFormMapper.toVisaApplicationFormEntity(visaApplicationRequestDTO);
        newVisaApplicationEntity.setStatus(VisaStatus.CREATE);
        newVisaApplicationEntity.setCreator(userEntityRepository.findById(0L).orElseThrow());
        VisaApplicationFormEntity visaApplicationFormInDB = visaApplicationFormEntityRepository.save(newVisaApplicationEntity);
        log.info("New visa application saved in DB with id {}", visaApplicationFormInDB.getId());
        return true;
    }

    @Transactional
    @Override
    public boolean deleteVisaApplication(long id) {
        log.info("Delete visa application by id {}", id);
        visaApplicationFormEntityRepository.findById(id).orElseThrow();
        visaApplicationFormEntityRepository.deleteById(id);
        log.info("Visa application with id {} successfully deleted", id);
        return true;
    }

    @Transactional(readOnly = true)
    @Override
    public List<VisaApplicationResponseDTO> getVisaApplications(Long countryId, LocalDate startDate, LocalDate endDate, VisaStatus visaStatus) {
        log.info("Get visa applications with params: start date={}, end date={}, visa status={}, country id ={}", startDate, endDate, visaStatus, countryId);
        List<VisaApplicationFormEntity> visaApplicationFormEntities
                = visaApplicationFormEntityRepository
                        .findVisaApplicationFormEntitiesByTravelCountryIdAndCreatedOnBetweenAndStatusOrderByCreatedOn(
                                countryId, startDate.atStartOfDay(), endDate.atTime(LocalTime.MAX), visaStatus);
        log.info("Found {} elements in DB", visaApplicationFormEntities.size());
        return visaApplicationFormMapper.toVisaApplicationResponseDTOList(visaApplicationFormEntities);
    }

    @Transactional(readOnly = true)
    @Override
    public Long getTouristIdByApplicationId(Long applicationId) {
        VisaApplicationFormEntity visaApplication = visaApplicationFormEntityRepository.findById(applicationId).orElseThrow();
        return visaApplication.getTourist().getId();
    }

    @Transactional(readOnly = true)
    @Override
    public VisaApplicationResponseDTO getVisaApplicationById(long id) {
        log.info("Get visa application by id {}", id);
        VisaApplicationFormEntity visaApplicationForm = visaApplicationFormEntityRepository.findById(id).orElseThrow();
        log.info("Visa application by id {} found", id);
        return Optional.ofNullable(visaApplicationFormMapper.toVisaApplicationResponseDTO(visaApplicationForm)).orElseThrow(NullPointerException::new);
    }

    @Transactional
    @Override
    public boolean changeVisaApplication(VisaApplicationRequestDTO visaApplicationRequestDTO, Long id) throws AccessDeniedException {
        log.info("Update visa application with id {}", id);
        VisaApplicationFormEntity visaApplicationForm = visaApplicationFormEntityRepository.findById(id).orElseThrow();
        log.info("Visa status is {}", visaApplicationForm.getStatus());
        if (!allowedStatuses.contains(visaApplicationForm.getStatus())){
            throw new AccessDeniedException("Visa application details cannot be changed after submission");
        }
        visaApplicationForm = visaApplicationFormMapper.updateVisaApplicationFormEntity(visaApplicationForm, visaApplicationRequestDTO);
        visaApplicationFormEntityRepository.save(visaApplicationForm);
        return true;
    }

    @Transactional
    @Override
    public void sendVisaApplicationsToVisaCenter(List<Long> ids) {
        log.info("Send {} visa applications to visa center", ids.size());
        if (ids.size() >= 101) throw new IllegalArgumentException("Can send not more than 100 visa applications");
        List<VisaApplicationFormEntity> visaApplicationFormEntities = visaApplicationFormEntityRepository.findAllById(ids);
        log.info("Found {} elements in DB", visaApplicationFormEntities.size());
        visaApplicationFormEntities.forEach(visaApplicationForm -> visaApplicationForm.setStatus(VisaStatus.SEND));
        visaApplicationFormEntityRepository.saveAll(visaApplicationFormEntities);
    }

    @Transactional(readOnly = true)
    @Override
    public List<VisaResponseDTO> getVisas(Long countryId, LocalDate startDate, LocalDate endDate) {
        log.info("Get visas by params: country id {}, start date {}, end date {}", countryId, startDate, endDate);
        List<EVisaEntity> eVisaEntities
                = eVisaEntityRepository.findEVisaEntitiesByCountryIdAndIssueDateBetweenOrderByIssueDate(countryId, startDate, endDate);
        log.info("Found {} elements in DB", eVisaEntities.size());
        return eVisaMapper.toVisaResponseDTOs(eVisaEntities);
    }

    @Transactional(readOnly = true)
    @Override
    public VisaResponseDTO getVisaById(Long id) {
        log.info("Get visa by id {}", id);
        EVisaEntity eVisaEntity = eVisaEntityRepository.findById(id).orElseThrow();
        return Optional.ofNullable(eVisaMapper.toVisaResponseDTO(eVisaEntity)).orElseThrow(NullPointerException::new);
    }

    @Override
    public void matchVisasByTourists() {
        dataMatcherService.matchVisasByTourists();
    }
}
