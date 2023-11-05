package com.transtour.travel.application.signature;

import com.transtour.kernel.domain.travel.TravelStatus;
import com.transtour.travel.application.signature.command.TravelSignatureCommand;
import com.transtour.travel.domain.Travel;
import com.transtour.travel.domain.TravelApproveException;
import com.transtour.travel.infrastructure.persistence.postgres.TravelRepository;
import com.transtour.travel.shared.util.ImageUtil;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Base64;

@Service
@Transactional
public class TravelSignatureUC {

    private final TravelRepository repository;
    private final ImageUtil imageUtil;

    public TravelSignatureUC(TravelRepository repository, ImageUtil imageUtil) {
        this.repository = repository;
        this.imageUtil = imageUtil;
    }

    public void saveSignature(TravelSignatureCommand command) throws IOException {
        Travel travel =repository.findById(command.getTravelId()).orElseThrow();
        if (!travel.getStatus().equals(TravelStatus.APPROVED))
            throw new TravelApproveException("No se ecuentra aprovado el viaje");


    }

}
