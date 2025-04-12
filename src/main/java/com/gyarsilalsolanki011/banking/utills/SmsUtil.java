package com.gyarsilalsolanki011.banking.utills;

import com.gyarsilalsolanki011.banking.cache.AppCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component
public class SmsUtil {
    @Autowired
    private AppCache appCache;

    @Autowired
    private RestTemplate restTemplate;

    public void sendSms(String message, String phoneNumber) {
        String url = "https://www.fast2sms.com/dev/bulkV2";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("authorization", appCache.appCache.get(AppCache.keys.SMS_API_KEY.toString()));

        Map<String, Object> body = new HashMap<>();
        body.put("route", "v3");
        body.put("sender_id", "TXTIND");
        body.put("message", message);
        body.put("language", "english");
        body.put("flash", 0);
        body.put("numbers", phoneNumber);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
            System.out.println("SMS sent: " + response.getBody());
        } catch (Exception e) {
            System.out.println("Failed to send SMS: " + e.getMessage());
        }
    }
}
