package com.rvafin.visacenter.dto.response;

import java.util.List;

public class MatchingResultDTO {

    private int numMatched;

    private int numUnmatched;

    private int totalNum;

    private List<UnmappedFieldsInfoDTO> unmappedFieldsInfoList;

    public MatchingResultDTO(){}

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

    public List<UnmappedFieldsInfoDTO> getUnmappedFieldsInfoList() {
        return unmappedFieldsInfoList;
    }

    public void setUnmappedFieldsInfoList(List<UnmappedFieldsInfoDTO> unmappedFieldsInfoList) {
        this.unmappedFieldsInfoList = unmappedFieldsInfoList;
    }
}
