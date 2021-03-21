package com.creativelabs.scriptscreator.service;

import com.creativelabs.scriptscreator.domain.Npc;
import com.creativelabs.scriptscreator.dto.NpcDto;
import com.creativelabs.scriptscreator.exception.NotFoundException;

import java.util.List;
import java.util.Optional;

public interface NpcService {
    List<Npc> getAllNpcs();
    Npc saveNpc(final Npc npc);
    Optional<Npc> getNpc(final Long id);
    void deleteNpc(final Long npcId) throws NotFoundException;
    Npc updateNpcById(final Long id, final NpcDto npcDto) throws NotFoundException;
}
