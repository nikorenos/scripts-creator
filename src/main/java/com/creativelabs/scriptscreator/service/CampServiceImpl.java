package com.creativelabs.scriptscreator.service;

import com.creativelabs.scriptscreator.domain.Camp;
import com.creativelabs.scriptscreator.dto.CampDto;
import com.creativelabs.scriptscreator.exception.NotFoundException;
import com.creativelabs.scriptscreator.mapper.NpcMapper;
import com.creativelabs.scriptscreator.repository.CampRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CampServiceImpl implements CampService {
    private final CampRepository repository;
    private final NpcMapper npcMapper;

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
        foundCamp.setNpcList(npcMapper.mapToNpcList(campDto.getNpcList()));
        saveCamp(foundCamp);
        return foundCamp;
    }
}
