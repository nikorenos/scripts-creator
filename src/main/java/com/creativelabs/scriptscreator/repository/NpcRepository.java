package com.creativelabs.scriptscreator.repository;

import com.creativelabs.scriptscreator.domain.Npc;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface NpcRepository extends CrudRepository<Npc, Long> {
    @Override
    List<Npc> findAll();

    @Override
    Optional<Npc> findById(Long NpcId);

    @Override
    void deleteById(Long NpcId);

    @Override
    long count();
}
