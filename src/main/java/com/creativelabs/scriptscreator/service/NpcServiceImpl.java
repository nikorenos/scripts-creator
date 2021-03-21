package com.creativelabs.scriptscreator.service;

import com.creativelabs.scriptscreator.domain.Camp;
import com.creativelabs.scriptscreator.domain.Npc;
import com.creativelabs.scriptscreator.dto.NpcDto;
import com.creativelabs.scriptscreator.exception.NotFoundException;
import com.creativelabs.scriptscreator.repository.CampRepository;
import com.creativelabs.scriptscreator.repository.NpcRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class NpcServiceImpl implements NpcService {
    private final NpcRepository npcRepository;
    private final CampRepository campRepository;

    @Override
    public List<Npc> getAllNpcs() {
        return npcRepository.findAll();
    }
    @Override
    public Npc saveNpc(final Npc npc) {
        int scriptId = generateScriptId();
        npc.setScriptId(scriptId);
        return npcRepository.save(npc);
    }
    @Override
    public Optional<Npc> getNpc(final Long npcId) {
        return npcRepository.findById(npcId);
    }

    @Override
    public void deleteNpc(final Long npcId) {
        Npc foundNpc = npcRepository.findById(npcId).orElseThrow(() -> new NotFoundException("Npc id: " + npcId +
                " not found in database"));
        Long campId = foundNpc.getCamp().getId();
        Camp foundCamp = campRepository.findById(campId).orElseThrow(() -> new NotFoundException("Camp id: " + campId +
                " not found in database"));
        foundCamp.getNpcList().remove(foundNpc);
        npcRepository.deleteById(npcId);
    }
    @Override
    public Npc updateNpcById(final Long id, final NpcDto npcDto) throws NotFoundException {
        Npc foundNpc = npcRepository.findById(id).orElseThrow(() -> new NotFoundException("Npc id: " + id +
                " not found in database"));
        foundNpc.setName(npcDto.getName());
        foundNpc.setDescription(npcDto.getDescription());
        Camp camp = campRepository.findById(npcDto.getCampId())
                .orElseThrow(() -> new NotFoundException("Camp id: " + npcDto.getCampId() + " not found"));
        foundNpc.setCamp(camp);
        saveNpc(foundNpc);
        return foundNpc;
    }

    public int generateScriptId() {
        List<Integer> scriptIdList = new ArrayList<>();
        if (npcRepository.findAll().size() == 0) {
            return 1;
        } else {
            for (Npc npc : npcRepository.findAll()) {
                scriptIdList.add(npc.getScriptId());
            }
            Collections.sort(scriptIdList);
            for (int i = 0; i < scriptIdList.size(); i++) {
                if (i+1 != scriptIdList.get(i)) {
                    return (i+1);
                }
            }
            return scriptIdList.size() + 1;
        }
    }
}
