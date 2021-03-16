package com.creativelabs.scriptscreator.service;

import com.creativelabs.scriptscreator.client.ImgurClient;
import com.creativelabs.scriptscreator.dto.imgur.ImageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ImgurService {
    private final ImgurClient imgurClient;

    public ImageDto fetchImgurImage(final String imageId) {
        return imgurClient.getImage(imageId);
    }

    public ImageDto uploadImage(final MediaType file) {
        ImageDto newImage = imgurClient.uploadImage(file);
        return newImage;
    }
}
