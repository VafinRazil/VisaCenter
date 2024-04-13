package com.rvafin.visacenter.service.interfaces;

import com.rvafin.visacenter.dto.request.CreateVisaApplicationRequestDTO;
import com.rvafin.visacenter.dto.response.VisaApplicationFormResponseDTO;
import com.rvafin.visacenter.dto.response.VisaResponseDTO;
import com.rvafin.visacenter.enums.VisaStatus;

import java.time.LocalDate;
import java.util.List;

public interface VisaDepartmentService {

    boolean createVisaApplication(CreateVisaApplicationRequestDTO createVisaApplicationRequestDTO);

    boolean deleteVisaApplication(long id);

    List<VisaApplicationFormResponseDTO> getVisaApplications(Long countryId, LocalDate startDate, LocalDate endDate, VisaStatus visaStatus);

    VisaApplicationFormResponseDTO getVisaApplicationById(long id);

    boolean changeVisaApplication(CreateVisaApplicationRequestDTO createVisaApplicationRequestDTO);

    void sendVisaApplicationsToVisaCenter(List<Long> ids);

    void handleSentVisaApplications();

    List<VisaResponseDTO> getVisas(Long countryId, LocalDate startDate, LocalDate endDate);

    VisaResponseDTO getVisaById(Long id);
}
