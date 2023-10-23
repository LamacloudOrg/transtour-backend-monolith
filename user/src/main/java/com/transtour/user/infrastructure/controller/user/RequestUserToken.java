package com.transtour.user.infrastructure.controller.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestUserToken {

    private Long id;
    private String fcmToken;
    private String device;
}
