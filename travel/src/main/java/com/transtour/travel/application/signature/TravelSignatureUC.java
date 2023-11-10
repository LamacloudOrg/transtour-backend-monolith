package com.transtour.travel.application.signature;

import com.transtour.kernel.domain.bus.DomainEvent;
import com.transtour.kernel.domain.bus.EventBus;
import com.transtour.kernel.domain.travel.TravelStatus;
import com.transtour.travel.application.signature.command.TravelSignatureCommand;
import com.transtour.travel.domain.Travel;
import com.transtour.travel.domain.TravelSignedEvent;
import com.transtour.travel.infrastructure.persistence.postgres.TravelRepository;
import com.transtour.travel.shared.util.ImageUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class TravelSignatureUC {

    private final TravelRepository repository;
    private final ImageUtil imageUtil;


    private final EventBus eventBus;

    public TravelSignatureUC(TravelRepository repository, ImageUtil imageUtil, @Qualifier("GuavaImpl") EventBus eventBus) {
        this.repository = repository;
        this.imageUtil = imageUtil;
        this.eventBus = eventBus;
    }

    public void saveSignature(TravelSignatureCommand command) throws IOException {
        Travel travel =repository.findById(command.getTravelId()).orElseThrow();
        if (travel.getStatus().equals(TravelStatus.APPROVED) || travel.getStatus().equals(TravelStatus.SIGNED)) {

            byte[] image = imageUtil.resizePngImage(
                    Base64.getDecoder().decode(command.getSignature())
                    , command.getWidth()
                    , command.getHegiht());

            travel.getPayload().setSignature(image);
            travel.setStatus(TravelStatus.SIGNED);
            repository.save(travel);

            eventBus.publish(voucherEvent(command.getTravelId()));

        }

    }

    private List<DomainEvent> voucherEvent(Long travelId) {

        return Collections.singletonList(TravelSignedEvent.create(
                UUID.randomUUID().toString(),
                travelId
        ));
    }

}
