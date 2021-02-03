package com.creativelabs.scriptscreator.service;

import com.creativelabs.scriptscreator.domain.Npc;
import com.creativelabs.scriptscreator.dto.NpcDto;
import com.creativelabs.scriptscreator.exception.NotFoundException;
import com.creativelabs.scriptscreator.repository.NpcRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class NpcServiceImpl implements NpcService {
    private final NpcRepository npcRepository;

    public List<Npc> getAllNpcs() {
        return npcRepository.findAll();
    }

    public Npc saveNpc(final Npc npc) {
        return npcRepository.save(npc);
    }

    public Optional<Npc> getNpc(final Long npcId) {
        return npcRepository.findById(npcId);
    }

    public void deleteById(final Long npcId) {
        npcRepository.deleteById(npcId);
    }

    @Override
    public Npc updateNpcById(final Long id, final NpcDto npcDto) throws NotFoundException {
        Npc foundNpc = npcRepository.findById(id).orElseThrow(() -> new NotFoundException("Npc id: " + id +
                " not found in Npc database"));
        foundNpc.setName(npcDto.getName());
        foundNpc.setDescription(npcDto.getDescription());
        saveNpc(foundNpc);
        return foundNpc;
    }
}
