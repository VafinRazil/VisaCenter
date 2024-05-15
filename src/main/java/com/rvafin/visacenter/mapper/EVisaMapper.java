package com.rvafin.visacenter.mapper;

import com.rvafin.visacenter.dto.response.VisaResponseDTO;
import com.rvafin.visacenter.entity.EVisaEntity;
import com.rvafin.visacenter.entity.VisaApplicationFormEntity;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Mapper
public interface EVisaMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "firstname", expression = "java(visaApplicationForm.getTourist().getFirstname())")
    @Mapping(target = "surname", expression = "java(visaApplicationForm.getTourist().getSurname())")
    @Mapping(target = "birthday", expression = "java(visaApplicationForm.getTourist().getBirthday())")
    @Mapping(target = "createdOn", ignore = true)
    @Mapping(target = "passNum", expression = "java(visaApplicationForm.getTourist().getInternPassNum())")
    @Mapping(target = "passSeries", expression = "java(visaApplicationForm.getTourist().getInternPassSeries())")
    @Mapping(target = "country", source = "travelCountry")
    @Mapping(target = "tourist", ignore = true)
    EVisaEntity toEVisaEntity(VisaApplicationFormEntity visaApplicationForm);

    @AfterMapping
    default void setAdditionalValues(
            @MappingTarget EVisaEntity eVisaEntity,
            VisaApplicationFormEntity visaApplicationForm
    ){
        eVisaEntity.setIssueDate(LocalDate.now());
        eVisaEntity.setNumber(UUID.randomUUID().toString());
    }


    @Mapping(target = "fullName", expression = "java(eVisaEntity.getFullName())")
    @Mapping(target = "countryName", expression = "java(eVisaEntity.getCountry().getName())")
    VisaResponseDTO toVisaResponseDTO(EVisaEntity eVisaEntity);

    List<VisaResponseDTO> toVisaResponseDTOs(List<EVisaEntity> eVisaEntities);
}
