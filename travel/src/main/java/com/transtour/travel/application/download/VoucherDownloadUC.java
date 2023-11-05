package com.transtour.travel.application.download;

import com.github.dozermapper.core.Mapper;
import com.transtour.kernel.domain.travel.TravelStatus;
import com.transtour.travel.application.signature.command.TravelSignatureCommand;
import com.transtour.travel.domain.Travel;
import com.transtour.travel.domain.TravelApproveException;
import com.transtour.travel.domain.TravelDTO;
import com.transtour.travel.domain.TravelNotReady;
import com.transtour.travel.infrastructure.persistence.postgres.TravelRepository;
import com.transtour.travel.shared.util.ImageUtil;
import com.transtour.travel.shared.util.VoucherUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

@Service
@Transactional
public class VoucherDownloadUC {

    private final TravelRepository repository;
    private final ImageUtil imageUtil;

    @Autowired
    Mapper mapper;

    public VoucherDownloadUC(TravelRepository repository, ImageUtil imageUtil) {
        this.repository = repository;
        this.imageUtil = imageUtil;
    }

    public void downloadVoucher(TravelSignatureCommand command) throws IOException {
        Travel travel =repository.findById(command.getTravelId()).orElseThrow();
        if (!travel.getStatus().equals(TravelStatus.APPROVED))
            throw new TravelApproveException("No se ecuentra aprovado el viaje");

        ArrayList<Map<String, Object>> pieceFieldDetailsMaps = new ArrayList<Map<String, Object>>();

        if (!travel.getStatus().equals(TravelStatus.READY)) throw new TravelNotReady();

        TravelDTO travelDTO = new TravelDTO();

        travelDTO.setReserveNumber(travel.getPayload().getReserveNumber());
        mapper.map(travel, travelDTO);
        byte[] document = travel.getPayload().getSignature();
        String signatureFile = VoucherUtil.path + travel.getOrderNumber();
    }
}
