package com.transtour.user.application.list_driver.query;

import com.transtour.kernel.domain.bus.query.QueryHandler;
import com.transtour.user.application.DriversResponse;
import com.transtour.user.application.list_driver.ListDriversUC;
import org.springframework.stereotype.Service;

@Service
public class ListDriversQueryHandler implements QueryHandler<ListDriversQuery, DriversResponse> {


    private final ListDriversUC useCase;

    public ListDriversQueryHandler(ListDriversUC useCase) {
        this.useCase = useCase;
    }

    @Override
    public DriversResponse handle(ListDriversQuery query) {
        return useCase.find(query);
    }
}
