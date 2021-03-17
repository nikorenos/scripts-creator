package com.creativelabs.scriptscreator.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CampServiceImpl implements CampService {

    /*
    @RequiredArgsConstructor
@Service
public class NpcServiceImpl implements NpcService {
    private final NpcRepository npcRepository;

    @Override
    public List<Npc> getAllNpcs() {
        return npcRepository.findAll();
    }
    @Override
    public Npc saveNpc(final Npc npc) {
        return npcRepository.save(npc);
    }
    @Override
    public Optional<Npc> getNpc(final Long npcId) {
        return npcRepository.findById(npcId);
    }
    @Override
    public void deleteNpcById(final Long npcId) {
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
    */
}
