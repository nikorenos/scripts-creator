package com.creativelabs.scriptscreator.controller;

import com.creativelabs.scriptscreator.dto.NpcDto;
import com.creativelabs.scriptscreator.exception.NotFoundException;
import com.creativelabs.scriptscreator.mapper.NpcMapper;
import com.creativelabs.scriptscreator.service.NpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/npcs")
public class NpcController {
    @Autowired
    private NpcMapper mapper;
    @Autowired
    private NpcService service;

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
        return mapper.mapToNpcDto(service.saveNpc(mapper.mapToNpc(npcDto)));
    }

    @DeleteMapping("{id}")
    public void deleteNpc(@PathVariable Long id) {
        service.deleteById(id);
    }
}
