package com.creativelabs.scriptscreator.repository;

import com.creativelabs.scriptscreator.domain.Camp;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CampRepository extends CrudRepository<Camp, Long> {
    @Override
    List<Camp> findAll();

    @Override
    Optional<Camp> findById(Long NpcId);

    @Override
    void deleteById(Long NpcId);

    @Override
    long count();
}
