package com.transtour.travel.application.list;

import com.transtour.kernel.domain.travel.TravelStatus;
import com.transtour.travel.application.TravelResponse;
import com.transtour.travel.application.list.query.ListTravelQuery;
import com.transtour.travel.infrastructure.persistence.postgres.TravelRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ListTravelUC {

    private final TravelRepository travelRepository;

    public ListTravelUC(TravelRepository travelRepository) {
        this.travelRepository = travelRepository;
    }

    public TravelResponse find(ListTravelQuery query) {


        if (query.getCardDriver() != null && query.getDateCreated() != null && query.getTravelStatus() != null) {
            return new TravelResponse(
                    travelRepository.findByCreatedAtAndCarDriver(
                            query.getDateCreated(),
                            query.getTravelStatus().toString(),
                            query.getCardDriver(),
                            query.getPageable()
                    )
            );

        }

        if (query.getCardDriver() != null && query.getDateCreated() != null && query.getTravelStatus() == null) {
            return new TravelResponse(travelRepository.findByCreatedAtAndStatus(
                    query.getDateCreated(),
                    TravelStatus.valueOf(query.getCardDriver()),
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
                    travelRepository.findByCreatedAtAndStatus(
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
                    travelRepository.findByCreatedAt(
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
