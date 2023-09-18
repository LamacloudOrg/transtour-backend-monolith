package com.transtour.kernel.domain.notification;

import com.transtour.kernel.domain.bus.DomainEvent;
import com.transtour.kernel.domain.travel.TravelStatus;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;


public class AndroidPushNotificationEvent extends DomainEvent {

    public AndroidPushNotificationEvent() {
    }

    public AndroidPushNotificationEvent(String aggregateId, Map<String, Serializable> attributes) {
        super(aggregateId, attributes);
    }

    public static NotificationEmailEvent create(String aggregateId,
                                                String to,
                                                Long travelId,
                                                TravelStatus travelStatus,
                                                String origin,
                                                String destiny,
                                                String passenger,
                                                String observation,
                                                LocalDate date,
                                                LocalTime time,
                                                String company,
                                                String carDriver,
                                                String waitingTime,
                                                String parkingAmount,
                                                String takForReturn,
                                                String toll
                                               ) {
        Map<String, Serializable> map = new HashMap<>();


        map.put("notificationId", aggregateId);
        map.put("to", to);
        map.put("travelId", travelId);
        map.put("status", travelStatus);
        map.put("origin", destiny);
        map.put("passenger", passenger);
        map.put("observation", observation);
        map.put("date", date);
        map.put("time", time);
        map.put("company", company);
        map.put("carDriver", carDriver);
        map.put("waitingTime", waitingTime);
        map.put("parkingAmount", parkingAmount);
        map.put("takForReturn", takForReturn);
        map.put("toll", toll);

        return new NotificationEmailEvent(aggregateId, map);

    }

    @Override
    public String eventName() {
        return "android.push.notification.event";
    }
}
