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
                npcDto.getTrelloCardId(),
                npcDto.getTrelloCardUrl(),
                npcDto.getAttachmentUrl()
        );
    }

    public NpcDto mapToNpcDto(final Npc npc) {
        return new NpcDto(
                npc.getId(),
                npc.getName(),
                npc.getDescription(),
                npc.getCamp().getId(),
                npc.getTrelloCardId(),
                npc.getTrelloCardUrl(),
                npc.getAttachmentUrl()
        );
    }

    public List<NpcDto> mapToNpcDtoList(final List<Npc> npcList) {
        return npcList.stream()
                .map(t -> new NpcDto(t.getId(), t.getName(), t.getDescription(), t.getCamp().getId(), t.getTrelloCardId(),
                        t.getTrelloCardUrl(), t.getAttachmentUrl()))
                .collect(Collectors.toList());
    }
}
