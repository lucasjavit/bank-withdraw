package com.lucasit.withdraw.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestCallerImpl implements RestCaller {

    private RestTemplate restTemplate = new RestTemplate();

    @Value("${base.url}")
    private String baseUrl;


    @Override
    public <T> ResponseEntity<T> callGet(String url, ParameterizedTypeReference<T> responseType) {
        return restTemplate.exchange(baseUrl + url, HttpMethod.GET, null, responseType);
    }

    @Override
    public <T> ResponseEntity<T> callPost(String uri, Object requestBody, ParameterizedTypeReference<T> responseType) {
        return restTemplate.exchange(baseUrl + uri, HttpMethod.POST, new HttpEntity<>(requestBody), responseType);
    }

}
