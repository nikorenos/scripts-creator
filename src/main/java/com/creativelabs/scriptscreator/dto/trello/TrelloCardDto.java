package com.creativelabs.scriptscreator.dto.trello;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TrelloCardDto {
    private String name;
    private String desc;
    private String pos;
    private String shortUrl;
    private String idAttachmentCover;
    private String idList;
    private String idBoard;
}