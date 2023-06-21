package com.transtour.travel.application.list_company;

import com.transtour.kernel.domain.bus.query.Response;
import lombok.Getter;

import java.util.List;
@Getter
public class CompaniesResponse implements Response {

    private final List<CompanyResponse> data;


    public CompaniesResponse(List<CompanyResponse> json) {
        this.data = json;
    }
}
