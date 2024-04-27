package com.rvafin.visacenter.service.interfaces;

import com.rvafin.visacenter.imprecise_data_matcher.interfaces.ImpreciseDataMatcher;
import com.rvafin.visacenter.dto.response.MatchingResultDTO;
import com.rvafin.visacenter.entity.EVisaEntity;
import com.rvafin.visacenter.entity.TouristEntity;

public interface DataMatcherService extends ImpreciseDataMatcher<Double, EVisaEntity, TouristEntity> {

    MatchingResultDTO matchVisasByTourists();
}
