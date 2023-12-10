package com.transtour.travel.application.read_voucher;

import com.transtour.travel.application.read_voucher.query.ReadVoucherQuery;
import com.transtour.travel.domain.Travel;
import com.transtour.travel.domain.TravelNotFoundException;
import com.transtour.travel.infrastructure.persistence.postgres.TravelRepository;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class ReadVoucherUC {

    private final TravelRepository repository;

    public ReadVoucherUC(TravelRepository repository) {
        this.repository = repository;
    }

    public VoucherResponse getVoucher(ReadVoucherQuery query) throws IOException {
        Travel travel = repository.findById(query.getTravelId()).orElseThrow(() -> new TravelNotFoundException(query.getTravelId().toString()));

        FileUtils.forceDelete(new File(travel.getPayload().getSignaturePath()));
        String fileName = travel.getPayload().getReportPath();
        byte[] pdf = FileUtils.readFileToByteArray(new File(fileName));

        return new VoucherResponse(fileName, pdf);
    }
}
