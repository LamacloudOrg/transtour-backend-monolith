package com.transtour.travel.application.list;

import com.transtour.travel.application.TravelResponse;
import com.transtour.travel.application.list.query.ListTravelQuery;
import com.transtour.travel.infrastructure.persistence.postgres.TravelRepository;
import org.springframework.stereotype.Service;

@Service
public class ListTravelUC {

    private final TravelRepository travelRepository;

    public ListTravelUC(TravelRepository travelRepository) {
        this.travelRepository = travelRepository;
    }

    public TravelResponse find(ListTravelQuery query) {


        if (query.getCardDriver() != null && query.getDateCreated() != null && query.getTravelStatus() != null) {
            return new TravelResponse(
                    travelRepository.findByDateCreatedAndStatusAndCarDriver(
                            query.getDateCreated(),
                            query.getTravelStatus(),
                            query.getCardDriver(),
                            query.getPageable()
                    )
            );

        }

        if (query.getCardDriver() != null && query.getDateCreated() != null && query.getTravelStatus() == null) {
            return new TravelResponse(travelRepository.findByDateCreatedAndCarDriver(
                    query.getDateCreated(),
                    query.getCardDriver(),
                    query.getPageable()
            )
            );
        }

        if (query.getCardDriver() != null && query.getDateCreated() == null && query.getTravelStatus() != null) {
            return new TravelResponse(
                    travelRepository.findByCarDriverAndStatus(
                            query.getCardDriver(),
                            query.getTravelStatus(),
                            query.getPageable()
                    )
            );
        }

        if (query.getCardDriver() == null && query.getDateCreated() != null && query.getTravelStatus() != null) {
            return new TravelResponse(
                    travelRepository.findByDateCreatedAndStatus(
                            query.getDateCreated(),
                            query.getTravelStatus(),
                            query.getPageable()
                    )
            );
        }


        if (query.getCardDriver() != null && query.getDateCreated() == null && query.getTravelStatus() == null) {
            return new TravelResponse(
                    travelRepository.findByCarDriver(
                            query.getCardDriver(),
                            query.getPageable()
                    )
            );
        }

        if (query.getCardDriver() == null && query.getDateCreated() != null && query.getTravelStatus() == null) {
            return new TravelResponse(
                    travelRepository.findByDateCreated(
                            query.getDateCreated(),
                            query.getPageable()
                    )
            );
        }

        if (query.getCardDriver() == null && query.getDateCreated() == null && query.getTravelStatus() != null) {
            return new TravelResponse(
                    travelRepository.findByStatus(
                            query.getTravelStatus(),
                            query.getPageable()
                    )
            );
        }

        return new TravelResponse(travelRepository.findAll(query.getPageable()));

    }
}
