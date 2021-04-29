package com.creativelabs.scriptscreator.service;

import com.creativelabs.scriptscreator.domain.Camp;
import com.creativelabs.scriptscreator.dto.CampDto;
import com.creativelabs.scriptscreator.exception.NotFoundException;

import java.util.List;
import java.util.Optional;

public interface CampService {
    List<Camp> getAllCamps();
    Camp saveCamp(final Camp camp);
    Optional<Camp> getCamp(final Long id);
    void deleteCampById(final Long id);
    Camp updateCampById(final Long id, final CampDto campDto) throws NotFoundException;
}
