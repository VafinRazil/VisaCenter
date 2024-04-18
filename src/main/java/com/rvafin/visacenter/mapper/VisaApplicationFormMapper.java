package com.rvafin.visacenter.mapper;

import com.rvafin.visacenter.dto.request.VisaApplicationRequestDTO;
import com.rvafin.visacenter.dto.response.VisaApplicationResponseDTO;
import com.rvafin.visacenter.entity.VisaApplicationFormEntity;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
public interface VisaApplicationFormMapper {

    VisaApplicationFormEntity toVisaApplicationFormEntity(VisaApplicationRequestDTO visaApplicationRequestDTO);

    VisaApplicationResponseDTO toVisaApplicationResponseDTO(VisaApplicationFormEntity visaApplicationFormEntity);

    List<VisaApplicationResponseDTO> toVisaApplicationResponseDTOList(List<VisaApplicationFormEntity> visaApplicationFormEntities);
}
