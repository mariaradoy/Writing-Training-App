package org.vaadin.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vaadin.example.entity.Status;
import org.vaadin.example.entity.StoryPlot;

import java.util.List;

@Repository
public interface StoryPlotRepository extends JpaRepository<StoryPlot, Long> {
    List<StoryPlot> findByStatus (Status status);
}
