package com.creativelabs.scriptscreator.service;

import com.creativelabs.scriptscreator.domain.Npc;
import com.creativelabs.scriptscreator.dto.NpcDto;
import com.creativelabs.scriptscreator.exception.NotFoundException;

public interface NpcService {
    Npc updateNpcById(final Long id, final NpcDto npcDto) throws NotFoundException;
}
