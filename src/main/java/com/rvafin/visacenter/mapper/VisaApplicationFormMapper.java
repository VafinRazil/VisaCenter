package com.rvafin.visacenter.mapper;

import com.rvafin.visacenter.dto.request.VisaApplicationRequestDTO;
import com.rvafin.visacenter.dto.response.VisaApplicationResponseDTO;
import com.rvafin.visacenter.entity.CountryEntity;
import com.rvafin.visacenter.entity.TouristEntity;
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


    @BeanMapping(qualifiedByName = "createNewVisaApplication")
    @Mapping(target = "tourist", ignore = true)
    @Mapping(target = "travelCountry", ignore = true)
    public abstract VisaApplicationFormEntity toVisaApplicationFormEntity(
            VisaApplicationRequestDTO visaApplicationRequestDTO
    ) throws NoSuchElementException;


    @Mapping(target = "fullName", expression = "java(visaApplicationFormEntity.getTourist().getFullName())")
    @Mapping(target = "birthday", expression = "java(visaApplicationFormEntity.getTourist().getBirthday())")
    @Mapping(target = "travelCountry", expression = "java(visaApplicationFormEntity.getTravelCountry().getName())")
    public abstract VisaApplicationResponseDTO toVisaApplicationResponseDTO(
            VisaApplicationFormEntity visaApplicationFormEntity
    );

    @BeanMapping(qualifiedByName = "updateVisaApplication")
    @Mapping(target = "travelCountry", ignore = true)
    public abstract VisaApplicationFormEntity updateVisaApplicationFormEntity(
            @MappingTarget VisaApplicationFormEntity visaApplicationForm,
            VisaApplicationRequestDTO visaApplicationRequestDTO
    );

    public abstract List<VisaApplicationResponseDTO> toVisaApplicationResponseDTOList(
            List<VisaApplicationFormEntity> visaApplicationFormEntities
    );

    @Named("createNewVisaApplication")
    @BeforeMapping
    public void createNewVisaApplication(
            @MappingTarget VisaApplicationFormEntity visaApplicationForm,
            VisaApplicationRequestDTO visaApplicationRequestDTO
    ){
        TouristEntity tourist = touristEntityRepository.findById(visaApplicationRequestDTO.getTouristId())
                .orElseThrow(() -> new NoSuchElementException("No such tourist in DB by id " + visaApplicationRequestDTO.getTouristId()));
        CountryEntity country = countryEntityRepository.findById(visaApplicationRequestDTO.getTravelCountryId())
                .orElseThrow(() -> new NoSuchElementException("No such country in DB by id " + visaApplicationRequestDTO.getTravelCountryId()));
        visaApplicationForm.setTourist(tourist);
        visaApplicationForm.setTravelCountry(country);
    }

    @Named("updateVisaApplication")
    @BeforeMapping
    public void updateVisaApplication(
            @MappingTarget VisaApplicationFormEntity visaApplicationForm,
            VisaApplicationRequestDTO visaApplicationRequestDTO
    ){
        if (Long.compare(visaApplicationForm.getTravelCountry().getId(), visaApplicationRequestDTO.getTravelCountryId()) != 0) {
            CountryEntity country = countryEntityRepository.findById(visaApplicationRequestDTO.getTravelCountryId()).orElseThrow();
            visaApplicationForm.setTravelCountry(country);
        }
        if (Long.compare(visaApplicationForm.getTourist().getId(), visaApplicationRequestDTO.getTouristId()) != 0) {
            TouristEntity tourist = touristEntityRepository.findById(visaApplicationRequestDTO.getTouristId()).orElseThrow();
            visaApplicationForm.setTourist(tourist);
        }
    }

}
