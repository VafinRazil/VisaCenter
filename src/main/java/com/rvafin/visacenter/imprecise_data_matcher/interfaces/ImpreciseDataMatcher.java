package com.rvafin.visacenter.imprecise_data_matcher.interfaces;


import com.rvafin.visacenter.imprecise_data_matcher.MatchingAlgorithm;

public interface ImpreciseDataMatcher<R, T extends StringFormatter, V extends StringFormatter>{

    R impreciseMatch(T data1, V data2, MatchingAlgorithm<R> algorithm);
}

