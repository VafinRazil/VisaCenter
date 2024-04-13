package com.rvafin.visacenter.service.interfaces;

import com.rvafin.visacenter.util.MatchingResult;

import java.time.LocalDate;

public interface DataMatcherService {
    MatchingResult matchVisasByTourists(LocalDate startDate, LocalDate finalDate);

    MatchingResult matchVisasByTourists();
}
