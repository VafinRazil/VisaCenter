package com.rvafin.visacenter.service;

import com.rvafin.visacenter.configuration.ImpreciseDataMatcherConfiguration;
import com.rvafin.visacenter.entity.EVisaEntity;
import com.rvafin.visacenter.entity.TouristEntity;
import com.rvafin.visacenter.entity.VisaApplicationFormEntity;
import com.rvafin.visacenter.enums.VisaStatus;
import com.rvafin.visacenter.imprecise_data_matcher.MatchingAlgorithm;
import com.rvafin.visacenter.imprecise_data_matcher.util.JaroWinklerDistance;
import com.rvafin.visacenter.repository.EVisaEntityRepository;
import com.rvafin.visacenter.repository.VisaApplicationFormEntityRepository;
import com.rvafin.visacenter.service.interfaces.DataMatcherService;
import com.rvafin.visacenter.repository.TouristEntityRepository;
import com.rvafin.visacenter.dto.response.MatchingResultDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Component
public class DataMatcherServiceImpl implements DataMatcherService {

    private static final Logger log = LoggerFactory.getLogger(DataMatcherServiceImpl.class);

    private final TouristEntityRepository touristEntityRepository;
    private final EVisaEntityRepository visaEntityRepository;
    private final VisaApplicationFormEntityRepository visaApplicationFormEntityRepository;
    private final ImpreciseDataMatcherConfiguration impreciseDataMatcherConfiguration;

    @Autowired
    public DataMatcherServiceImpl(
            TouristEntityRepository touristEntityRepository,
            EVisaEntityRepository visaEntityRepository,
            VisaApplicationFormEntityRepository visaApplicationFormEntityRepository,
            ImpreciseDataMatcherConfiguration impreciseDataMatcherConfiguration
    ) {
        this.touristEntityRepository = touristEntityRepository;
        this.visaEntityRepository = visaEntityRepository;
        this.visaApplicationFormEntityRepository = visaApplicationFormEntityRepository;
        this.impreciseDataMatcherConfiguration = impreciseDataMatcherConfiguration;
    }

    @Transactional
    @Override
    public MatchingResultDTO matchVisasByTourists() {
        MatchingResultDTO matchingResultDTO = new MatchingResultDTO();
        List<EVisaEntity> unmatchedVisas = new ArrayList<>();
        visaEntityRepository.findEVisaEntitiesByTouristIsNull().forEach(visa -> {
            Optional<TouristEntity> touristOpt = getTouristForVisa(visa);
            touristOpt.ifPresentOrElse(tourist -> {
                Optional<VisaApplicationFormEntity> visaApplication = tourist.getSentVisaApplicationByCountryId(visa.getCountry().getId());
                updateVisaApplicationStatus(visaApplication);
                tourist.addEVisa(visa);
                touristEntityRepository.save(tourist);
            }, () -> unmatchedVisas.add(visa));
        });
        return matchUnmatchedVisas(unmatchedVisas, matchingResultDTO);
    }

    @Override
    public Double impreciseMatch(
            EVisaEntity eVisa,
            TouristEntity tourist,
            MatchingAlgorithm<Double> algorithm
    ) {
        String touristInfoInVisa = eVisa.format(impreciseDataMatcherConfiguration.getDelimiter());
        String touristInfoInEntity = tourist.format(impreciseDataMatcherConfiguration.getDelimiter());
        return algorithm.apply(touristInfoInVisa, touristInfoInEntity);
    }

    private MatchingResultDTO matchUnmatchedVisas(
            List<EVisaEntity> unmatchedVisas,
            MatchingResultDTO matchingResultDTO
    ){
        List<TouristEntity> touristsWithoutVisa = touristEntityRepository.findTouristEntitiesWithVisaInStatusSend();
        Set<Long> matchedTouristIds = new HashSet<>();
        unmatchedVisas.forEach(visa -> {
            Optional<TouristEntity> touristOpt = getTouristForVisaOfTouristsList(visa, touristsWithoutVisa, matchedTouristIds);
            touristOpt.ifPresent(tourist -> {
                Optional<VisaApplicationFormEntity> visaApplication = tourist.getSentVisaApplicationByCountryId(visa.getCountry().getId());
                updateVisaApplicationStatus(visaApplication);
                tourist.addEVisa(visa);
                touristEntityRepository.save(tourist);
            });
        });
        return matchingResultDTO;
    }

    private void updateVisaApplicationStatus(
            Optional<VisaApplicationFormEntity> visaApplication
    ){
        visaApplication.ifPresent(visaApplicationForm -> {
            visaApplicationForm.setStatus(VisaStatus.READY);
            visaApplicationFormEntityRepository.save(visaApplicationForm);
        });
    }

    private Optional<TouristEntity> getTouristForVisaOfTouristsList(
            EVisaEntity eVisa,
            List<TouristEntity> touristsWithoutVisa,
            Set<Long> matchedTouristIds
    ) {
        MatchingAlgorithm<Double> matchingAlgorithm = new JaroWinklerDistance();
        return touristsWithoutVisa
                .stream()
                .filter(tourist -> !matchedTouristIds.contains(tourist.getId()))
                .takeWhile(tourist -> {
                    double percentage = impreciseMatch(eVisa, tourist, matchingAlgorithm);
                    return !(percentage > 90d);
                }).findAny();
    }

    private Optional<TouristEntity> getTouristForVisa(
            EVisaEntity eVisa
    ){
        List<TouristEntity> touristEntities = touristEntityRepository.findTouristEntitiesByInternPassNumAndInternPassSeriesAndCountryId(eVisa.getPassNum(), eVisa.getPassSeries(), eVisa.getCountry().getId());
        if (touristEntities.size() == 1){
            return Optional.ofNullable(touristEntities.get(0));
        }else{
            return touristEntities
                    .stream()
                    .filter(tourist -> tourist.getFirstname().equalsIgnoreCase(eVisa.getFirstname())
                            && tourist.getSurname().equalsIgnoreCase(eVisa.getSurname())
                            && tourist.getBirthday().isEqual(eVisa.getBirthday()))
                    .findAny();
        }
    }
}
