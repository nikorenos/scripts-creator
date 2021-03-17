package com.creativelabs.scriptscreator.service;

import com.creativelabs.scriptscreator.domain.Camp;
import com.creativelabs.scriptscreator.domain.Npc;
import com.creativelabs.scriptscreator.dto.CampDto;
import com.creativelabs.scriptscreator.dto.NpcDto;
import com.creativelabs.scriptscreator.exception.NotFoundException;
import com.creativelabs.scriptscreator.repository.CampRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CampServiceImpl implements CampService {
    private final CampRepository repository;

    @Override
    public List<Camp> getAllCamps() {
        return repository.findAll();
    }
    @Override
    public Optional<Camp> getCamp(final Long campId) {
        return repository.findById(campId);
    }

    @Override
    public Camp saveCamp(final Camp camp) {
        return repository.save(camp);
    }

    @Override
    public void deleteCampById(final Long campId) {
        repository.deleteById(campId);
    }

    @Override
    public Camp updateCampById(final Long id, final CampDto campDto) throws NotFoundException {
        Camp foundCamp = repository.findById(id).orElseThrow(() -> new NotFoundException("Camp id: " + id +
                " not found in database"));
        foundCamp.setName(campDto.getName());
        foundCamp.setDescription(campDto.getDescription());
        saveCamp(foundCamp);
        return foundCamp;
    }
}
