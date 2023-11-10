package com.transtour.travel.application.read_voucher.query;

import com.transtour.kernel.domain.bus.query.Query;
import lombok.Getter;

@Getter
public class ReadVoucherQuery implements Query {

    private final Long travelId;

    public ReadVoucherQuery(Long travelId) {
        this.travelId = travelId;
    }
}
