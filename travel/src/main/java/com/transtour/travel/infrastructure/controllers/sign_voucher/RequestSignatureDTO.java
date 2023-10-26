package com.transtour.travel.infrastructure.controllers.sign_voucher;

import lombok.Data;
import org.springframework.lang.NonNull;

@Data
public class RequestSignatureDTO {
    @NonNull
    private int width;
    @NonNull
    private int height;
    @NonNull
    private String image;
}
