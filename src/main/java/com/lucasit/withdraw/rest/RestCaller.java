package com.lucasit.withdraw.rest;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;

public interface RestCaller {

    <T> ResponseEntity<T> callGet(String uri, ParameterizedTypeReference<T> responseType);


    <T> ResponseEntity<T> callPost(String uri, Object requestBody, ParameterizedTypeReference<T> responseType);


}
