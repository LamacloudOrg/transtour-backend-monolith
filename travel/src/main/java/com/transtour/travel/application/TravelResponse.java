package com.transtour.travel.application;

import com.transtour.kernel.domain.bus.query.Response;
import com.transtour.travel.domain.Travel;
import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
public class TravelResponse implements Response {

    private final Page<Travel> data;

    public TravelResponse(Page<Travel> page) {
        this.data = page;
    }
}
