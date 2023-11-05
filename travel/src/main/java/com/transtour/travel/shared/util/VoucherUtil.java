package com.transtour.travel.shared.util;

import lombok.NonNull;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class VoucherUtil {

    public static String path = "/opt/app/voucher/";
    public static String jasperFile = "/opt/app/voucher.jrxml";

    public static Map<String, Object> mapDetail(@NonNull Object o) {
        Map<String, Object> detail = new HashMap<>();
        Field[] fields = o.getClass().getDeclaredFields();
        Arrays.stream(fields).forEach(field -> {
            try {
                detail.put(field.getName(), Optional.ofNullable(field.get(o).toString()).orElse(""));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });

        return detail;
    }
}
