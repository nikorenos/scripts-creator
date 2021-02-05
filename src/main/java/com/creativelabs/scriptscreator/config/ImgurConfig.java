package com.creativelabs.scriptscreator.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class ImgurConfig {
    @Value("${imgur.api.endpoint.prod}")
    private String imgurApiEndpoint;

    @Value("${imgur.app.client_id}")
    private String imgurClientId;
}
