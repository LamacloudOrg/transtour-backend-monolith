package com.transtour.travel.application.list_company;

import com.transtour.kernel.domain.bus.query.Response;
import lombok.Getter;

import java.util.List;
@Getter
public class CompaniesResponse implements Response {

    private final List<CompanyResponse> company;


    public CompaniesResponse(List<CompanyResponse> json) {
        this.company = json;
    }
}
