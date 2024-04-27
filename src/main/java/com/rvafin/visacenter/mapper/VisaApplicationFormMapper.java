package com.rvafin.visacenter.mapper;

import com.rvafin.visacenter.dto.request.VisaApplicationRequestDTO;
import com.rvafin.visacenter.dto.response.VisaApplicationResponseDTO;
import com.rvafin.visacenter.entity.CountryEntity;
import com.rvafin.visacenter.entity.VisaApplicationFormEntity;
import com.rvafin.visacenter.repository.CountryEntityRepository;
import com.rvafin.visacenter.repository.TouristEntityRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.NoSuchElementException;

@Mapper(componentModel = "spring")
public abstract class VisaApplicationFormMapper {

    @Autowired
    protected TouristEntityRepository touristEntityRepository;

    @Autowired
    protected CountryEntityRepository countryEntityRepository;

    @Mapping(target = "tourist",
            expression = "java(touristEntityRepository.findById(visaApplicationRequestDTO.getTouristId()).orElseThrow(() -> new NoSuchElementException(\"No such tourist in DB by id \" + visaApplicationRequestDTO.getTouristId())))")
    @Mapping(target = "travelCountry",
            expression = "java(countryEntityRepository.findById(visaApplicationRequestDTO.getTravelCountryId()).orElseThrow(() -> new NoSuchElementException(\"No such country in DB by id \" + visaApplicationRequestDTO.getTravelCountryId())))")
    public abstract VisaApplicationFormEntity toVisaApplicationFormEntity(
            VisaApplicationRequestDTO visaApplicationRequestDTO) throws NoSuchElementException;

    @Mapping(target = "fullName", expression = "java(visaApplicationFormEntity.getTourist().getFullName())")
    @Mapping(target = "birthday", expression = "java(visaApplicationFormEntity.getTourist().getBirthday())")
    @Mapping(target = "travelCountry", expression = "java(visaApplicationFormEntity.getTravelCountry().getName())")
    public abstract VisaApplicationResponseDTO toVisaApplicationResponseDTO(
            VisaApplicationFormEntity visaApplicationFormEntity);

    public abstract List<VisaApplicationResponseDTO> toVisaApplicationResponseDTOList(
            List<VisaApplicationFormEntity> visaApplicationFormEntities);

    @BeforeMapping
    public void setCountryEntityWithCondition(@MappingTarget VisaApplicationFormEntity visaApplicationForm, VisaApplicationRequestDTO visaApplicationRequestDTO){
        if (Long.compare(visaApplicationForm.getTravelCountry().getId(), visaApplicationRequestDTO.getTravelCountryId()) != 0) {
            CountryEntity country = countryEntityRepository.findById(visaApplicationRequestDTO.getTravelCountryId()).orElseThrow();
            visaApplicationForm.setTravelCountry(country);
        }
    }

    @Mapping(target = "travelCountry", ignore = true)
    public abstract VisaApplicationFormEntity updateVisaApplicationFormEntity(@MappingTarget VisaApplicationFormEntity visaApplicationForm, VisaApplicationRequestDTO visaApplicationRequestDTO);

}
