package com.rvafin.visacenter.service;

import com.rvafin.visacenter.entity.EVisaEntity;
import com.rvafin.visacenter.entity.TouristEntity;
import com.rvafin.visacenter.entity.VisaApplicationFormEntity;
import com.rvafin.visacenter.enums.VisaStatus;
import com.rvafin.visacenter.repository.EVisaEntityRepository;
import com.rvafin.visacenter.service.interfaces.DataMatcherService;
import com.rvafin.visacenter.repository.TouristEntityRepository;
import com.rvafin.visacenter.util.JaroWinklerDistance;
import com.rvafin.visacenter.dto.response.MatchingResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
public class DataMatcherServiceImpl implements DataMatcherService {

    private final TouristEntityRepository touristEntityRepository;

    private final EVisaEntityRepository visaEntityRepository;

    @Autowired
    public DataMatcherServiceImpl(
            TouristEntityRepository touristEntityRepository,
            EVisaEntityRepository visaEntityRepository
    ) {
        this.touristEntityRepository = touristEntityRepository;
        this.visaEntityRepository = visaEntityRepository;
    }

    @Override
    public MatchingResultDTO matchVisasByTourists() {
        List<EVisaEntity> visas = visaEntityRepository.findEVisaEntitiesByTouristIsNull();
        for (EVisaEntity eVisa:visas){
            List<TouristEntity> touristEntities =
                    touristEntityRepository
                            .findTouristEntitiesByInternPassNumAndInternPassSeriesAndCountryId(
                                    eVisa.getPassNum(), eVisa.getPassSeries(), eVisa.getCountry().getId());
            if (touristEntities.size() == 1){
                TouristEntity tourist = touristEntities.get(0);
                eVisa.setTourist(tourist);
                Optional<VisaApplicationFormEntity> visaApplication = tourist.getVisaApplicationFormEntities()
                        .stream()
                        .filter(visaApplicationForm -> visaApplicationForm.getTravelCountry().getId().compareTo(eVisa.getCountry().getId()) == 0 && visaApplicationForm.getStatus().equals(VisaStatus.SEND))
                        .findAny();
            }
        }
        return null;
    }


}
