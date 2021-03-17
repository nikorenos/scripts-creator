package com.creativelabs.scriptscreator.dto;

import com.creativelabs.scriptscreator.domain.Npc;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CampDto {
    private Long id;
    private String name;
    private String description;
    private List<Npc> npcList;
}
