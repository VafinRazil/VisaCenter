package com.rvafin.visacenter.service;

import com.rvafin.visacenter.dto.request.CreateVisaApplicationRequestDTO;
import com.rvafin.visacenter.dto.response.VisaApplicationFormResponseDTO;
import com.rvafin.visacenter.dto.response.VisaResponseDTO;
import com.rvafin.visacenter.enums.VisaStatus;
import com.rvafin.visacenter.service.interfaces.VisaDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class VisaDepartmentServiceImpl implements VisaDepartmentService {

    public VisaDepartmentServiceImpl(){

    }

    @Override
    public boolean createVisaApplication(CreateVisaApplicationRequestDTO createVisaApplicationRequestDTO) {
        return false;
    }

    @Override
    public boolean deleteVisaApplication(long id) {
        return false;
    }

    @Override
    public List<VisaApplicationFormResponseDTO> getVisaApplications(Long countryId, LocalDate startDate, LocalDate endDate, VisaStatus visaStatus) {
        return List.of();
    }

    @Override
    public VisaApplicationFormResponseDTO getVisaApplicationById(long id) {
        return null;
    }

    @Override
    public boolean changeVisaApplication(CreateVisaApplicationRequestDTO createVisaApplicationRequestDTO) {
        return false;
    }

    @Override
    public void sendVisaApplicationsToVisaCenter(List<Long> ids) {

    }

    @Override
    public void handleSentVisaApplications() {

    }

    @Override
    public List<VisaResponseDTO> getVisas(Long countryId, LocalDate startDate, LocalDate endDate) {
        return List.of();
    }

    @Override
    public VisaResponseDTO getVisaById(Long id) {
        return null;
    }
}
