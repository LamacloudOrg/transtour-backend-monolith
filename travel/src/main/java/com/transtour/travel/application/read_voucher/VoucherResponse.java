package com.transtour.travel.application.read_voucher;

import com.transtour.kernel.domain.bus.query.Response;
import lombok.Getter;

@Getter
public class VoucherResponse implements Response {

    private final String pdfName;
    private final byte[] pdf;

    public VoucherResponse(String pdfName, byte[] pdf) {
        this.pdfName = pdfName;
        this.pdf = pdf;
    }
}
