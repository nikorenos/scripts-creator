package com.creativelabs.scriptscreator.controller;

import com.creativelabs.scriptscreator.domain.image.Image;
import com.creativelabs.scriptscreator.dto.imgur.ImageDto;
import com.creativelabs.scriptscreator.service.ImgurService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/imgur")
@RequiredArgsConstructor
public class ImgurController {
    private final ImgurService imgurService;

    @GetMapping("image/{imageId}")
    public ImageDto getImgurImage(@PathVariable String imageId) {
        return imgurService.fetchImgurImage(imageId);
    }

    @PostMapping
    public ImageDto uploadImage(@RequestBody Image file) {
        return imgurService.uploadImage(file);
    }
}
