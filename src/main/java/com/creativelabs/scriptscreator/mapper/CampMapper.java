package com.creativelabs.scriptscreator.mapper;

import com.creativelabs.scriptscreator.domain.Camp;
import com.creativelabs.scriptscreator.dto.CampDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CampMapper {
    private final NpcMapper npcMapper;

    public Camp mapToCamp(final CampDto campDto) {
        return new Camp(
                campDto.getId(),
                campDto.getName(),
                campDto.getDescription(),
                npcMapper.mapToNpcList(campDto.getNpcList())
        );
    }

    public CampDto mapToCampDto(final Camp camp) {
        return new CampDto(
                camp.getId(),
                camp.getName(),
                camp.getDescription(),
                npcMapper.mapToNpcDtoList(camp.getNpcList())
        );
    }

    public List<CampDto> mapToCampDtoList(final List<Camp> campList) {
        return campList.stream()
                .map(t -> new CampDto(t.getId(), t.getName(), t.getDescription(), npcMapper.mapToNpcDtoList(t.getNpcList())))
                .collect(Collectors.toList());
    }
}