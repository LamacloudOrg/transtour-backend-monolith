package com.transtour.notification.application.android_notification.command;

import com.transtour.kernel.domain.bus.command.Command;
import com.transtour.kernel.domain.travel.TravelStatus;
import lombok.Getter;
import lombok.NonNull;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;
@Getter
public class AndroidNotificationCommand implements Command {

    private final   Map<String, String> info;
    private final  String to;


    public AndroidNotificationCommand(
            @NonNull String to,
            @NonNull UUID notificationId,
            @NonNull Long travelId,
            @NonNull TravelStatus status,
            @NonNull String origin,
            @NonNull String destiny,
            @NonNull String passenger,
            Optional<String>  observation,
            @NonNull LocalDate date,
            @NonNull LocalTime time,
            @NonNull String company,
            @NonNull String carDriver,
            Optional<Double> waitingTime,
            Optional<Double> parkingAmount,
            Optional<Double> takForReturn,
            Optional<Double> toll
            ){
        this.to = to;
        info = new HashMap<>();
        info.put("id",notificationId.toString());
        info.put("orderNumber",String.valueOf(travelId));
        info.put("status", String.valueOf(status));
        info.put("origin",origin);
        info.put("destiny",destiny);
        info.put("passenger",passenger);
        info.put("observation",observation.orElseGet(()->""));
        //ZonedDateTime zonedDateTime = addZoneDateTime(date,time);
        info.put("date", String.valueOf(date));
        info.put("time", String.valueOf(time));
        info.put("company",company.toLowerCase().trim());
        info.put("carDriver",company.toLowerCase().trim());
        info.put("waitingTime", String.valueOf(waitingTime.orElseGet(()-> new Double(0))));
        info.put("parkingAmount", String.valueOf(parkingAmount.orElseGet(()-> new Double(0))));
        info.put("takForReturn", String.valueOf(takForReturn.orElseGet(()-> new Double(0))));
        info.put("toll", String.valueOf(toll.orElseGet(()-> new Double(0))));
    }

    private static ZonedDateTime addZoneDateTime(LocalDate localDate,LocalTime localTime){
        return ZonedDateTime.of(localDate,localTime, ZoneId.of("America/Argentina/Buenos Aires"));
    }


    @Override
    public HashMap<String, Serializable> toPrimitives() {
        return new HashMap<>();
    }
}
