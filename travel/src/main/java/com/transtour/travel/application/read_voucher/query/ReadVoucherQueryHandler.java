package com.transtour.travel.application.read_voucher.query;

import com.transtour.kernel.domain.bus.query.QueryHandler;
import com.transtour.travel.application.read_voucher.ReadVoucherUC;
import com.transtour.travel.application.read_voucher.VoucherResponse;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

@Component
public class ReadVoucherQueryHandler implements QueryHandler<ReadVoucherQuery, VoucherResponse> {

    private final ReadVoucherUC uc;

    public ReadVoucherQueryHandler(ReadVoucherUC uc) {
        this.uc = uc;
    }


    @Override
    @SneakyThrows
    public VoucherResponse handle(ReadVoucherQuery query) {
        return uc.getVoucher(query);
    }
}
