package com.creativelabs.scriptscreator.mapper;

import com.creativelabs.scriptscreator.domain.Camp;
import com.creativelabs.scriptscreator.dto.CampDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CampMapper {
    public Camp mapToCamp(final CampDto campDto) {
        return new Camp(
                campDto.getId(),
                campDto.getName(),
                campDto.getDescription(),
                campDto.getNpcList()
        );
    }

    public CampDto mapToCampDto(final Camp camp) {
        return new CampDto(
                camp.getId(),
                camp.getName(),
                camp.getDescription(),
                camp.getNpcList()
        );
    }

    public List<CampDto> mapToCampDtoList(final List<Camp> campList) {
        return campList.stream()
                .map(t -> new CampDto(t.getId(), t.getName(), t.getDescription(), t.getNpcList()))
                .collect(Collectors.toList());
    }
}