package com.rvafin.visacenter.service.interfaces;

import com.rvafin.visacenter.dto.request.VisaApplicationRequestDTO;
import com.rvafin.visacenter.dto.response.VisaApplicationResponseDTO;
import com.rvafin.visacenter.dto.response.VisaResponseDTO;
import com.rvafin.visacenter.enums.VisaStatus;

import java.nio.file.AccessDeniedException;
import java.time.LocalDate;
import java.util.List;

public interface VisaDepartmentService {

    boolean createVisaApplication(VisaApplicationRequestDTO visaApplicationRequestDTO);

    boolean deleteVisaApplication(long id);

    List<VisaApplicationResponseDTO> getVisaApplications(Long countryId, LocalDate startDate, LocalDate endDate, VisaStatus visaStatus);

    List<VisaApplicationResponseDTO> getVisaApplicationsForClient();

    VisaApplicationResponseDTO getVisaApplicationById(long id);

    boolean changeVisaApplication(VisaApplicationRequestDTO visaApplicationRequestDTO, Long id) throws AccessDeniedException;

    void sendVisaApplicationsToVisaCenter(List<Long> ids);

    List<VisaResponseDTO> getVisas(Long countryId, LocalDate startDate, LocalDate endDate);

    VisaResponseDTO getVisaById(Long id);
}
