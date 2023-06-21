package com.transtour.travel.application.list_company;

import com.transtour.travel.application.list_company.query.ListCompanyQuery;
import com.transtour.travel.domain.Company;
import com.transtour.travel.infrastructure.persistence.jpa.CompanyRepository;


import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class GetCompanyUC {

    private final CompanyRepository companyRepository;

    public GetCompanyUC(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public CompaniesResponse findAll(ListCompanyQuery query) {
        List<Company> companyList = companyRepository.findAll();

        List<CompanyResponse> companies = companyList.stream().map(company ->
            new CompanyResponse(
                    company.getId(),
                    company.getFullName(),
                    company.getNickName()
            )).collect(Collectors.toList());
        return new CompaniesResponse(companies);
    }

}
