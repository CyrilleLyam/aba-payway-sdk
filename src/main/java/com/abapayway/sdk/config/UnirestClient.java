package com.abapayway.sdk.config;

import kong.unirest.Unirest;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class UnirestClient {

    private final PaywayProperties props;

    public UnirestClient(PaywayProperties props) {
        this.props = props;
    }

    @PostConstruct
    public void setup() {
        Unirest.config().defaultBaseUrl(props.getApiUrl()).connectTimeout(0).socketTimeout(0);
    }
}
