package com.creativelabs.scriptscreator.mapper;

import com.creativelabs.scriptscreator.domain.Camp;
import com.creativelabs.scriptscreator.domain.Npc;
import com.creativelabs.scriptscreator.dto.NpcDto;
import com.creativelabs.scriptscreator.exception.NotFoundException;
import com.creativelabs.scriptscreator.repository.CampRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class NpcMapper {
    private final CampRepository campRepository;

    public Npc mapToNpc(final NpcDto npcDto) {
        Npc npc = new Npc();
        npc.setId(null);
        npc.setName(npcDto.getName());
        npc.setDescription(npcDto.getDescription());
        Camp camp = campRepository.findById(npcDto.getCampId())
                .orElseThrow(() -> new NotFoundException("Camp id: " + npcDto.getCampId() + " not found"));
        npc.setCamp(camp);

        return npc;
    }

    public NpcDto mapToNpcDto(final Npc npc) {
        NpcDto npcDto = new NpcDto();
        npcDto.setId(npc.getId());
        npcDto.setName(npc.getName());
        npcDto.setDescription(npc.getDescription());
        npcDto.setCampId(npc.getCamp().getId());

        return npcDto;
    }

    public List<NpcDto> mapToNpcDtoList(final List<Npc> npcList) {
        return npcList.stream()
                .map(this::mapToNpcDto)
                .collect(Collectors.toList());
    }
}
