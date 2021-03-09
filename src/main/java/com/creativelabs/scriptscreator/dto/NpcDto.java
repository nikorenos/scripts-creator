package com.creativelabs.scriptscreator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NpcDto {
    private Long id;
    private String name;
    private String description;
    private String location;
    private String trelloCardId;
    private String trelloCardUrl;
    private String attachmentUrl;
}
