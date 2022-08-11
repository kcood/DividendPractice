package com.dayone.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class ScrapedResult {

    private Company company; //스크래핑한 회사 정보
    private List<Dividend> dividends; //한 회사가 여러개 배당금 정보 갖고있으니 리스트로
    public ScrapedResult(){
        this.dividends = new ArrayList<>();} //

}
