package com.mfscreener.mfapp.web.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;

public class MfAppClientException extends RuntimeException {
    private final HttpStatusCode statusCode;
    private final HttpHeaders headers;

    public MfAppClientException(HttpStatusCode statusCode, HttpHeaders headers) {
        this.statusCode = statusCode;
        this.headers = headers;
    }

    public HttpStatusCode getStatusCode() {
        return statusCode;
    }

    public HttpHeaders getHeaders() {
        return headers;
    }
}
