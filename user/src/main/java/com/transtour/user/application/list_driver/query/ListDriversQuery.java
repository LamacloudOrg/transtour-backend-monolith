package com.transtour.user.application.list_driver.query;

import com.transtour.kernel.domain.bus.query.Query;
import lombok.Getter;

@Getter
public class ListDriversQuery implements Query {

    private final String rolName;

    public ListDriversQuery(String rolName) {
        this.rolName = rolName;
    }
}
