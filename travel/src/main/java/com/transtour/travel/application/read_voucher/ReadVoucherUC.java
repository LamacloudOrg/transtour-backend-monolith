package com.transtour.travel.application.read_voucher;

import com.transtour.travel.application.read_voucher.query.ReadVoucherQuery;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class ReadVoucherUC {

    private static final String FILE_PATH = "./files/";

    public VoucherResponse getVoucher(ReadVoucherQuery query) throws IOException {
        String fileName = FILE_PATH + query.getTravelId() + ".pdf";
        byte[] pdf = FileUtils.readFileToByteArray(new File(fileName));
        return new VoucherResponse(fileName, pdf);
    }
}
