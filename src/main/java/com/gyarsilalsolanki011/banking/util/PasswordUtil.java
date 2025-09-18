package com.gyarsilalsolanki011.banking.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
public class PasswordUtil {
    private static final BCryptPasswordEncoder ENCODER = new BCryptPasswordEncoder();

    public static String encode(String rawPassword) {
        return ENCODER.encode(rawPassword);
    }

}
