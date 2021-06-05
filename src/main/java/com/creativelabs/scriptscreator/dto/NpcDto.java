package com.creativelabs.scriptscreator.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NpcDto {
    private Long id;
    private Integer scriptId;
    private String name;
    private String description;
    private Long campId;
    private String location;
    private String trelloCardId;
    private String trelloCardUrl;
    private String attachmentUrl;

    public NpcDto(Long id, String name, String description, String trelloCardId, String trelloCardUrl, String attachmentUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.trelloCardId = trelloCardId;
        this.trelloCardUrl = trelloCardUrl;
        this.attachmentUrl = attachmentUrl;
    }
}
