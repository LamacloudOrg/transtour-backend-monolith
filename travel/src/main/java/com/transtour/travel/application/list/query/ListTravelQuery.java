package com.transtour.travel.application.list.query;

import com.transtour.kernel.domain.bus.query.Query;
import com.transtour.kernel.domain.travel.TravelStatus;
import lombok.Getter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.Map;

@Getter
public class ListTravelQuery implements Query {
    private final Pageable pageable;
    private String cardDriver;
    private TravelStatus travelStatus;
    private LocalDate dateCreated;

    public ListTravelQuery(Map<String, Object> params) {
        Integer page = Integer.valueOf((String) params.getOrDefault("page", "1"));
        Integer size = Integer.valueOf((String) params.getOrDefault("size", "100"));
        String sortBy = (String) params.getOrDefault("sortBy", "dateCreated");
        this.pageable = PageRequest.of(0, 3, Sort.by(sortBy).descending());

        if (params.containsKey("carDriver")) {
            this.cardDriver = (String) params.get("carDriver");
        }

        if (params.containsKey("status")) {
            this.travelStatus = TravelStatus.valueOf((String) params.get("status"));
        }

        if (params.containsKey("dateCreated")) {
            this.dateCreated = (LocalDate) params.get("dateCreated");
        }

    }

}
