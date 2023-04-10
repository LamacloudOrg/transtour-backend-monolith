package com.transtour.user.application.list_driver.query;

import com.transtour.kernel.domain.bus.query.QueryHandler;
import com.transtour.user.application.DriversResponse;
import com.transtour.user.application.list_driver.GetDriversUC;
import org.springframework.stereotype.Service;

@Service
public class ListDriversQueryHandler implements QueryHandler<ListDriversQuery, DriversResponse> {

    private final GetDriversUC useCase;

    public ListDriversQueryHandler(GetDriversUC useCase) {
        this.useCase = useCase;
    }

    @Override
    public DriversResponse handle(ListDriversQuery query) {
        return useCase.find(query);
    }
}
