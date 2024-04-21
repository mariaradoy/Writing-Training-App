package org.vaadin.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.example.entity.World;
import org.vaadin.example.repository.WorldRepository;

import java.util.List;
import java.util.Optional;

@Service
public class WorldService {

    private final WorldRepository worldRepository;

    @Autowired
    public WorldService(WorldRepository worldRepository) {
        this.worldRepository = worldRepository;
    }

    public List<World> all() {
        return  worldRepository.findAll();
    }


    public Optional<World> getWorldById(Long worldId) {
        return worldRepository.findById(worldId);
    }

    public void newWorld(World world) {
        worldRepository.save(world);
    }

    public World updateWorld(World updatedWorld, Long worldId) {
        return worldRepository.save(worldRepository.findById(worldId)
                .map(world -> {
                    world.setWorldName(updatedWorld.getWorldName());
                    world.setHistoryText(updatedWorld.getHistoryText());
                    world.setEconomyText(updatedWorld.getEconomyText());
                    world.setPoliticsText(updatedWorld.getPoliticsText());
                    world.setReligionText(updatedWorld.getReligionText());
                    world.setGeographyText(updatedWorld.getGeographyText());
                    world.setCultureText(updatedWorld.getCultureText());
                    world.setInhabitantsText(updatedWorld.getInhabitantsText());
                    world.setMagicText(updatedWorld.getMagicText());

                    return worldRepository.save(updatedWorld);
                })
                .orElseGet(() -> {
                    updatedWorld.setId(worldId);
                    return worldRepository.save(new World());
                }));
    }

    public boolean deleteWorld(Long worldId) {
        worldRepository.deleteById(worldId);
        return true;
    }
}
