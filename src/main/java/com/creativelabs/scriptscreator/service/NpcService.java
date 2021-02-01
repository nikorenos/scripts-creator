package com.creativelabs.scriptscreator.service;

import com.creativelabs.scriptscreator.domain.Npc;
import com.creativelabs.scriptscreator.repository.NpcRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class NpcService {
    private NpcRepository npcRepository;

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
}
