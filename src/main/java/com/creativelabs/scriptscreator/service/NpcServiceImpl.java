package com.creativelabs.scriptscreator.service;

import com.creativelabs.scriptscreator.domain.Camp;
import com.creativelabs.scriptscreator.domain.Npc;
import com.creativelabs.scriptscreator.dto.NpcDto;
import com.creativelabs.scriptscreator.exception.NotFoundException;
import com.creativelabs.scriptscreator.repository.CampRepository;
import com.creativelabs.scriptscreator.repository.NpcRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class NpcServiceImpl implements NpcService {
    private final NpcRepository repository;
    private final CampRepository campRepository;

    @Override
    public List<Npc> getAllNpcs() {
        return repository.findAll();
    }
    @Override
    public Npc saveNpc(final Npc npc) {
        return repository.save(npc);
    }
    @Override
    public Optional<Npc> getNpc(final Long npcId) {
        return repository.findById(npcId);
    }
    @Override
    public void deleteNpcById(final Long npcId) {
        repository.deleteById(npcId);
    }
    @Override
    public Npc updateNpcById(final Long id, final NpcDto npcDto) throws NotFoundException {
        Npc foundNpc = repository.findById(id).orElseThrow(() -> new NotFoundException("Npc id: " + id +
                " not found in database"));
        foundNpc.setName(npcDto.getName());
        foundNpc.setDescription(npcDto.getDescription());
        Camp camp = campRepository.findById(npcDto.getCampId())
                .orElseThrow(() -> new NotFoundException("Camp id: " + npcDto.getCampId() + " not found"));
        foundNpc.setCamp(camp);
        saveNpc(foundNpc);
        return foundNpc;
    }
}
