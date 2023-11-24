package com.transtour.travel.application.list.query;

import com.transtour.kernel.domain.bus.query.Query;
import com.transtour.kernel.domain.travel.TravelStatus;
import lombok.Getter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

@Getter
public class ListTravelQuery implements Query {
    private final Pageable pageable;
    private String cardDriver;
    private TravelStatus travelStatus;
    private LocalDateTime dateCreated;

    public ListTravelQuery(Map<String, Object> params) {
        int currentPage = Integer.parseInt((String) params.getOrDefault("page", "0"));
        int page = currentPage != 0 ? currentPage -1: 0 ;
        int size = Integer.parseInt((String) params.getOrDefault("size", "100"));

        String sortBy = (String) params.getOrDefault("sortBy", "createdAt");
        this.pageable = PageRequest.of(page, size, Sort.by(sortBy).descending());

        if (params.containsKey("carDriver")) {
            this.cardDriver = (String) params.get("carDriver");
        }

        if (params.containsKey("status")) {
            this.travelStatus = TravelStatus.valueOf((String) params.get("status"));
        }

        if (params.containsKey("dateCreated")) {
            this.dateCreated = (LocalDateTime) params.get("dateCreated");
        }

    }

}
