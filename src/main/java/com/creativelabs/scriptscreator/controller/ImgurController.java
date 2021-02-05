package com.creativelabs.scriptscreator.controller;

import com.creativelabs.scriptscreator.dto.imgur.ImageDto;
import com.creativelabs.scriptscreator.service.ImgurService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/imgur")
@RequiredArgsConstructor
public class ImgurController {
    private final ImgurService imgurService;

    @GetMapping("image/{imageId}")
    public ImageDto getImgurImage(@PathVariable String imageId) {
        return imgurService.fetchImgurImage(imageId);
    }
}
