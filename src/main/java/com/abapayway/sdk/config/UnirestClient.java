package com.abapayway.sdk.config;

import jakarta.annotation.PreDestroy;
import kong.unirest.Unirest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class UnirestClient {
    private static final Logger log = LoggerFactory.getLogger(UnirestClient.class);
    private final PaywayProperties props;

    public UnirestClient(PaywayProperties props) {
        this.props = props;
    }

    @PostConstruct
    public void setup() {
        Unirest.config().defaultBaseUrl(props.getApiUrl()).followRedirects(false).connectTimeout(0).socketTimeout(0);
    }

    @PreDestroy
    public void shutdown() {
        Unirest.shutDown();
        log.info("Unirest client shut down cleanly.");
    }

}
