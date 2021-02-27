package com.creativelabs.scriptscreator.mapper;

import com.creativelabs.scriptscreator.domain.Npc;
import com.creativelabs.scriptscreator.dto.NpcDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class NpcMapper {
    public Npc mapToNpc(final NpcDto npcDto) {
        return new Npc(
                npcDto.getId(),
                npcDto.getName(),
                npcDto.getDescription(),
                npcDto.getLocation(),
                npcDto.getTrelloCardId(),
                npcDto.getTrelloCardUrl()
        );
    }

    public NpcDto mapToNpcDto(final Npc npc) {
        return new NpcDto(
                npc.getId(),
                npc.getName(),
                npc.getDescription(),
                npc.getLocation(),
                npc.getTrelloCardId(),
                npc.getTrelloCardUrl()
        );
    }

    public List<NpcDto> mapToNpcDtoList(final List<Npc> npcList) {
        return npcList.stream()
                .map(t -> new NpcDto(t.getId(), t.getName(), t.getDescription(), t.getLocation(), t.getTrelloCardId(), t.getTrelloCardUrl()))
                .collect(Collectors.toList());
    }
}
