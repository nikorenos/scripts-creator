package com.creativelabs.scriptscreator.client;

import com.creativelabs.scriptscreator.config.ImgurConfig;
import com.creativelabs.scriptscreator.domain.image.Image;
import com.creativelabs.scriptscreator.dto.imgur.ImageDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import static java.util.Optional.ofNullable;

@RequiredArgsConstructor
@Component
public class ImgurClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(ImgurClient.class);

    private final RestTemplate restTemplate;
    private final ImgurConfig imgurConfig;

    public ImageDto getImage(String imageId) {
        URI url = UriComponentsBuilder.fromHttpUrl(imgurConfig.getImgurApiEndpoint() + "/image/" + imageId + "/authorize")
                .queryParam("client_id", imgurConfig.getImgurClientId())
                .queryParam("fields", "name,link").build().encode().toUri();
        try {
            ImageDto imageResponse = restTemplate.getForObject(url, ImageDto.class);
            return (ofNullable(imageResponse).orElse(new ImageDto()));
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(),e);
            return new ImageDto();
        }
    }

    public ImageDto uploadImage(Image file) {
        URI url = UriComponentsBuilder.fromHttpUrl(imgurConfig.getImgurApiEndpoint() + "/upload/authorize")
                .queryParam("client_id", imgurConfig.getImgurClientId())
                .queryParam("image", file.getImage()).build().encode().toUri();
        return restTemplate.postForObject(url, null, ImageDto.class);
    }
}