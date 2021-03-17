package com.creativelabs.scriptscreator.controller;

import com.creativelabs.scriptscreator.dto.NpcDto;
import com.creativelabs.scriptscreator.exception.NotFoundException;
import com.creativelabs.scriptscreator.mapper.CampMapper;
import com.creativelabs.scriptscreator.mapper.NpcMapper;
import com.creativelabs.scriptscreator.service.NpcServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/camps")
public class CampController {
    private final CampMapper mapper;
    private final NpcServiceImpl service;

    @GetMapping
    public List<NpcDto> getNpcs() {
        return mapper.mapToNpcDtoList(service.getAllNpcs());
    }

    @GetMapping("/{id}")
    public NpcDto getNpc(@PathVariable Long id) throws NotFoundException {
        return mapper.mapToNpcDto(service.getNpc(id).orElseThrow(() -> new NotFoundException("Npc id: " + id +
                " not found in Npc database")));
    }

    @PostMapping
    public void createNpc(@RequestBody NpcDto npcDto) {
        service.saveNpc(mapper.mapToNpc(npcDto));
    }

    @PutMapping("{id}")
    public NpcDto updateNpc(@PathVariable Long id, @RequestBody NpcDto npcDto) {
        return mapper.mapToNpcDto(service.updateNpcById(id, npcDto));
    }

    @DeleteMapping("{id}")
    public void deleteNpc(@PathVariable Long id) {
        service.deleteNpcById(id);
    }
}