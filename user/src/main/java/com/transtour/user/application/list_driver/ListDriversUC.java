package com.transtour.user.application.list_driver;

import com.transtour.user.application.DriversResponse;
import com.transtour.user.application.list_driver.query.ListDriversQuery;
import org.springframework.stereotype.Service;

@Service
public class ListDriversUC {
    public DriversResponse find(ListDriversQuery query) {

        return new DriversResponse();
    }
}
