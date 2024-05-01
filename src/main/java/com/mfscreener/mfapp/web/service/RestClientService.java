package com.mfscreener.mfapp.web.service;

import com.mfscreener.mfapp.web.exception.MfAppClientException;
import java.net.URI;
import java.util.Map;
import java.util.function.Function;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriBuilder;

@Service
public class RestClientService {

    private final RestClient restClient;

    public RestClientService(RestClient restClient) {
        this.restClient = restClient;
    }

    public String getForObject(String amfiWebsiteLink) {
        return callServer(uriBuilder -> URI.create(amfiWebsiteLink), HttpMethod.GET, null, null, String.class);
    }

    private <T> T callServer(
            Function<UriBuilder, URI> uriFunction,
            HttpMethod httpMethod,
            Map<String, String> headers,
            Object body,
            Class<T> bodyType) {
        RestClient.RequestBodySpec uri = restClient.method(httpMethod).uri(uriFunction);
        if (!CollectionUtils.isEmpty(headers)) {
            uri.headers(httpHeader -> headers.keySet().forEach(key -> httpHeader.add(key, headers.get(key))));
        }
        if (body != null) {
            uri.body(body);
        }
        RestClient.ResponseSpec responseSpec = uri.retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
                    throw new MfAppClientException(response.getStatusCode(), response.getHeaders());
                });

        return responseSpec.body(bodyType);
    }
}
