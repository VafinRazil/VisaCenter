package com.rvafin.visacenter.service.interfaces;

import com.rvafin.visacenter.dto.response.MatchingResultDTO;

import java.time.LocalDate;

public interface DataMatcherService {

    MatchingResultDTO matchVisasByTourists();
}
