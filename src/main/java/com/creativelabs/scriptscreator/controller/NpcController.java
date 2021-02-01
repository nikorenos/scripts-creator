package com.creativelabs.scriptscreator.controller;

import com.creativelabs.scriptscreator.dto.NpcDto;
import com.creativelabs.scriptscreator.exception.NotFoundException;
import com.creativelabs.scriptscreator.mapper.NpcMapper;
import com.creativelabs.scriptscreator.service.NpcService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/npc")
@RequiredArgsConstructor
public class NpcController {
    private NpcController controller;
    private NpcMapper mapper;
    private NpcService service;

    @GetMapping
    public List<NpcDto> getTasks() {
        return mapper.mapToNpcDtoList(service.getAllNpcs());
    }
    @GetMapping("/{id}")
    public NpcDto getNpc(@PathVariable Long id) throws NotFoundException {
        return mapper.mapToNpcDto(service.getNpc(id).orElseThrow(() -> new NotFoundException("Order id: " + id +
                " not found in Order database")));
    }
}
