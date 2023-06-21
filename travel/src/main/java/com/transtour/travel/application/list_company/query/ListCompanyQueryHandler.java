package com.transtour.travel.application.list_company.query;

import com.transtour.kernel.domain.bus.query.QueryHandler;
import com.transtour.travel.application.list_company.CompaniesResponse;
import com.transtour.travel.application.list_company.CompanyResponse;
import com.transtour.travel.application.list_company.GetCompanyUC;
import org.springframework.stereotype.Service;

@Service
public class ListCompanyQueryHandler implements QueryHandler<ListCompanyQuery, CompaniesResponse> {

    private final GetCompanyUC useCase;

    public ListCompanyQueryHandler(GetCompanyUC useCase) {
        this.useCase = useCase;
    }

    @Override
    public CompaniesResponse handle(ListCompanyQuery query) {
        return useCase.findAll(query);
    }
}
