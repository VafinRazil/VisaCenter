package com.rvafin.visacenter.imprecise_data_matcher;

public interface MatchingAlgorithm<R> {

    R apply(String line1, String line2);
}

