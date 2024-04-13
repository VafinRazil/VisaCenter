package com.rvafin.visacenter.util;

import java.util.List;

public class MatchingResult {

    private int numMatched;

    private int numUnmatched;

    private int totalNum;

    private List<UnmappedFieldsInfo> unmappedFieldsInfoList;

    public MatchingResult(){}

    public int getNumMatched() {
        return numMatched;
    }

    public void setNumMatched(int numMatched) {
        this.numMatched = numMatched;
    }

    public int getNumUnmatched() {
        return numUnmatched;
    }

    public void setNumUnmatched(int numUnmatched) {
        this.numUnmatched = numUnmatched;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public List<UnmappedFieldsInfo> getUnmappedFieldsInfoList() {
        return unmappedFieldsInfoList;
    }

    public void setUnmappedFieldsInfoList(List<UnmappedFieldsInfo> unmappedFieldsInfoList) {
        this.unmappedFieldsInfoList = unmappedFieldsInfoList;
    }
}
