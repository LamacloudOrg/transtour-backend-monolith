package com.transtour.travel.application.aprove;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.transtour.kernel.domain.bus.EventBus;
import com.transtour.kernel.domain.notification.AndroidPushNotificationEvent;
import com.transtour.kernel.domain.notification.NotificationTravelEmailEvent;
import com.transtour.kernel.domain.travel.TravelStatus;
import com.transtour.kernel.exceptions.UserNotExists;
import com.transtour.travel.application.aprove.command.TravelApproveCommand;
import com.transtour.travel.domain.Travel;
import com.transtour.travel.domain.TravelApproveException;
import com.transtour.travel.domain.TravelNotFoundException;
import com.transtour.travel.infrastructure.persistence.postgres.TravelRepository;
import com.transtour.user.domain.User;
import com.transtour.user.infrastructure.persistence.jpa.UserRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class TravelApproveUC {

    private final TravelRepository travelRepository;

    private final EventBus eventBus;
    private final UserRepository userRepository;

    public TravelApproveUC(TravelRepository travelRepository,
                           @Qualifier("GuavaImpl") EventBus eventBus,
                           @Qualifier("userRepo") UserRepository userRepository) {
        this.travelRepository = travelRepository;
        this.eventBus = eventBus;
        this.userRepository = userRepository;
    }

    public void approve(TravelApproveCommand command) throws JsonProcessingException {

        Travel travel = travelRepository.findById(command.getId()).orElseThrow(() -> new TravelNotFoundException("travel :" + command.getId().toString() + " not found"));

        if (!travel.getStatus().equals(TravelStatus.CREATED))
            throw new TravelApproveException("El viaje ya fue aprobado o cancelado");

        User user = userRepository.findByDni(travel.getCarDriver()).orElseThrow(UserNotExists::new);

        travel.setStatus(TravelStatus.APPROVED);
        travelRepository.save(travel);

        eventBus.publish(List.of(
                NotificationTravelEmailEvent.create(
                        UUID.randomUUID().toString(),
                        travel.getPayload().getPassengerEmail(),
                        "Nuevo Viaje Asignado",
                        "",
                        travelInfo(travel)
                ),

                AndroidPushNotificationEvent.create(
                        UUID.randomUUID().toString(),
                        user.getFirebaseToken().getToken(),
                        travel.getOrderNumber(),
                        travel.getStatus(),
                        travel.getPayload().getOriginAddress(),
                        travel.getPayload().getDestinyAddress(),
                        travel.getPayload().getPassengerName(),
                        travel.getPayload().getObservation(),
                        travel.getCreatedAt().toLocalDate(),
                        travel.getPayload().getTime(),
                        travel.getCompany(),
                        travel.getPayload().getCarDriverName(),
                        travel.getPayload().getWhitingTime(),
                        travel.getPayload().getParkingAmount(),
                        travel.getPayload().getTaxForReturn(),
                        travel.getPayload().getToll()
                )
        ));

    }

    private HashMap<String, String> travelInfo(Travel travel) {

        HashMap<String, String> travelNotificationInfo = new HashMap<>();

        travelNotificationInfo.put("signature", "TransTour");
        travelNotificationInfo.put("location", "Capital Federal");
        travelNotificationInfo.put("origin", travel.getPayload().getOriginAddress());
        travelNotificationInfo.put("destiny", travel.getPayload().getDestinyAddress());
        travelNotificationInfo.put("driver", travel.getCarDriver());
        travelNotificationInfo.put("date", travel.getPayload().getDateCreated().toString());
        travelNotificationInfo.put("time", travel.getPayload().getTime().toString());
        travelNotificationInfo.put("passengerName", travel.getPayload().getPassengerName());
        travelNotificationInfo.put("observation", travel.getPayload().getObservation());

        return travelNotificationInfo;

    }
}
