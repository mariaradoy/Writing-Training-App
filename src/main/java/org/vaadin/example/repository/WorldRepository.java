package org.vaadin.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vaadin.example.entity.World;

public interface WorldRepository extends JpaRepository<World, Long> {
}
