package com.transtour.travel.application.list.query;

import com.transtour.kernel.domain.bus.query.QueryHandler;
import com.transtour.travel.application.TravelResponse;
import com.transtour.travel.application.list.ListTravelUC;
import org.springframework.stereotype.Service;

@Service
public class ListTravelQueryHandler implements QueryHandler<ListTravelQuery, TravelResponse> {

    private final ListTravelUC useCase;

    public ListTravelQueryHandler(ListTravelUC useCase) {
        this.useCase = useCase;
    }

    @Override
    public TravelResponse handle(ListTravelQuery query) {
        return useCase.find(query);
    }
}
