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
}
