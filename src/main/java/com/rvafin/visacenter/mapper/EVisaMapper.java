package com.rvafin.visacenter.mapper;

import com.rvafin.visacenter.dto.response.VisaResponseDTO;
import com.rvafin.visacenter.entity.EVisaEntity;
import com.rvafin.visacenter.entity.VisaApplicationFormEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface EVisaMapper {

    EVisaEntity toEVisaEntity(VisaApplicationFormEntity visaApplicationForm);

    VisaResponseDTO toVisaResponseDTO(EVisaEntity eVisaEntity);

    List<VisaResponseDTO> toVisaResponseDTOs(List<EVisaEntity> eVisaEntities);
}
