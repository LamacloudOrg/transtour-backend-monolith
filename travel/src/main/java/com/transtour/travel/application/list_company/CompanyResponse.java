package com.transtour.travel.application.list_company;

import com.transtour.kernel.domain.bus.query.Response;
import lombok.Getter;

@Getter
public class CompanyResponse  {

    private String id;
    private String fullName;
    private String nickName;

    public CompanyResponse(String id, String fullName, String nickName) {
        this.id = id;
        this.fullName = fullName;
        this.nickName = nickName;
    }
}
