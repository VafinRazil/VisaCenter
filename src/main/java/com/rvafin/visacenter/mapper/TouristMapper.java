package com.rvafin.visacenter.mapper;

import com.rvafin.visacenter.dto.request.TouristRequestDTO;
import com.rvafin.visacenter.dto.response.TouristResponseDTO;
import com.rvafin.visacenter.entity.CountryEntity;
import com.rvafin.visacenter.entity.TouristEntity;
import com.rvafin.visacenter.repository.CountryEntityRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.NoSuchElementException;

@Mapper(componentModel = "spring")
public abstract class TouristMapper {

    @Autowired
    protected CountryEntityRepository countryEntityRepository;

    @BeanMapping(qualifiedByName = "creatingNewTourist")
    public abstract TouristEntity toTouristEntity(TouristRequestDTO touristRequestDTO);

    @BeanMapping(qualifiedByName = "updateTouristInfo")
    public abstract TouristEntity updateTouristEntity(@MappingTarget TouristEntity touristEntity, TouristRequestDTO touristRequestDTO);

    @Mapping(target = "countryId", expression = "java(touristEntity.getCountry().getId())")
    public abstract TouristResponseDTO toTouristResponseDTO(TouristEntity touristEntity);

    @Named("creatingNewTourist")
    @AfterMapping
    public void setCountry(
            @MappingTarget TouristEntity touristEntity,
            TouristRequestDTO touristRequestDTO
    ) {
        CountryEntity country = countryEntityRepository.findById(touristRequestDTO.getCountryId())
                .orElseThrow(() -> new NoSuchElementException("No such country in DB by id " + touristRequestDTO.getCountryId()));
        touristEntity.setCountry(country);
    }

    @Named("updateTouristInfo")
    @AfterMapping
    public void setCountryWithCondition(
            @MappingTarget TouristEntity touristEntity,
            TouristRequestDTO touristRequestDTO
    ) {
        if (Long.compare(touristEntity.getCountry().getId(), touristRequestDTO.getCountryId()) != 0) {
            CountryEntity country = countryEntityRepository.findById(touristRequestDTO.getCountryId())
                    .orElseThrow(() -> new NoSuchElementException("No such country in DB by id " + touristRequestDTO.getCountryId()));
            touristEntity.setCountry(country);
        }
    }
}
