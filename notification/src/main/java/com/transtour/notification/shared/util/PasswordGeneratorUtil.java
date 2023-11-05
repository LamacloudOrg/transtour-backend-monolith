package com.transtour.notification.shared.util;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class PasswordGeneratorUtil {

    public String generate(int n) {
        Random rand = new Random(); //instance of random class
        int upperbound = 10;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            stringBuilder.append(rand.nextInt(upperbound));
        }
        return stringBuilder.toString();
    }
}
