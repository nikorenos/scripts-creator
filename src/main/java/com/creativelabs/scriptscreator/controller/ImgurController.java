package com.creativelabs.scriptscreator.controller;

import com.creativelabs.scriptscreator.dto.imgur.ImageDto;
import com.creativelabs.scriptscreator.service.ImgurService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
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
    public ImageDto uploadImage(@RequestBody MediaType file) {
        return imgurService.uploadImage(file);
    }

    /*private MultipartFile image;
    @RequestMapping(method = RequestMethod.POST, consumes = { "multipart/form-data" })
    public @ResponseBody void addPhotoData(@ModelAttribute ImageDto imageDto) {

    }*/
}
