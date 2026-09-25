package com.automationintesting.requests;

import com.automationintesting.model.db.Message;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Collections;

public class MessageRequests {

    private String host;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public MessageRequests() {
        if(System.getenv("messageDomain") == null){
            host = "http://localhost:3006";
        } else {
            host = "http://" + System.getenv("messageDomain") + ":3006";
        }
    }

    public boolean postMessage(Message message){
        RestTemplate restTemplate = new RestTemplate();

        try {
            byte[] body = objectMapper.writeValueAsString(message).getBytes(StandardCharsets.UTF_8);

            HttpHeaders requestHeaders = new HttpHeaders();
            requestHeaders.setContentType(MediaType.APPLICATION_JSON);
            requestHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            requestHeaders.setContentLength(body.length);

            HttpEntity<byte[]> httpEntity = new HttpEntity<>(body, requestHeaders);

            ResponseEntity<String> response = restTemplate.exchange(host + "/message/", HttpMethod.POST, httpEntity, String.class);
            return response.getStatusCode().isSameCodeAs(HttpStatus.OK);
        } catch (HttpClientErrorException e){
            return false;
        } catch (Exception e){
            return false;
        }
    }

}
