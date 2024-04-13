package com.rvafin.visacenter.service;

import com.rvafin.visacenter.repository.EVisaEntityRepository;
import com.rvafin.visacenter.service.interfaces.DataMatcherService;
import com.rvafin.visacenter.repository.TouristEntityRepository;
import com.rvafin.visacenter.util.JaroWinklerDistance;
import com.rvafin.visacenter.util.MatchingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

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
    public MatchingResult matchVisasByTourists(LocalDate startDate, LocalDate finalDate) {
        double a = JaroWinklerDistance.apply("","");
        return null;
    }

    @Override
    public MatchingResult matchVisasByTourists() {
        return null;
    }
}
