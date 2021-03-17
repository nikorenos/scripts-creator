package com.creativelabs.scriptscreator.controller;

import com.creativelabs.scriptscreator.dto.CampDto;
import com.creativelabs.scriptscreator.dto.NpcDto;
import com.creativelabs.scriptscreator.exception.NotFoundException;
import com.creativelabs.scriptscreator.mapper.CampMapper;
import com.creativelabs.scriptscreator.mapper.NpcMapper;
import com.creativelabs.scriptscreator.service.CampServiceImpl;
import com.creativelabs.scriptscreator.service.NpcServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/camps")
public class CampController {
    private final CampMapper mapper;
    private final CampServiceImpl service;

    @GetMapping
    public List<CampDto> getCamps() {
        return mapper.mapToCampDtoList(service.getAllCamps());
    }

    @GetMapping("/{id}")
    public CampDto getCamp(@PathVariable Long id) throws NotFoundException {
        return mapper.mapToCampDto(service.getCamp(id).orElseThrow(() -> new NotFoundException("Camp id: " + id +
                " not found in database")));
    }

    @PostMapping
    public void createCamp(@RequestBody CampDto campDto) {
        service.saveCamp(mapper.mapToCamp(campDto));
    }

    @PutMapping("{id}")
    public CampDto updateCamp(@PathVariable Long id, @RequestBody CampDto campDto) {
        return mapper.mapToCampDto(service.updateCampById(id, campDto));
    }

    @DeleteMapping("{id}")
    public void deleteCamp(@PathVariable Long id) {
        service.deleteCampById(id);
    }
}
