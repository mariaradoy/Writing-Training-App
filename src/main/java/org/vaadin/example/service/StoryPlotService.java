package org.vaadin.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.example.entity.StoryPlot;
import org.vaadin.example.repository.StoryPlotRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StoryPlotService {

    private final StoryPlotRepository storyPlotRepository;

    @Autowired
    public StoryPlotService(StoryPlotRepository storyPlotRepository) {
        this.storyPlotRepository = storyPlotRepository;
    }

    public List<StoryPlot> all() {return storyPlotRepository.findAll();}

    public Optional<StoryPlot> getStoryPlotById(Long storyPlotId) {
      return storyPlotRepository.findById(storyPlotId);
    }
    public void newStory(StoryPlot storyPlot) {
        storyPlotRepository.save(storyPlot);
    }

    public StoryPlot updateStoryPlote(StoryPlot updatedStoryPlot, Long id) {
        return storyPlotRepository.save(storyPlotRepository.findById(id)
                .map(storyPlot -> {
                    storyPlot.setStoryName(updatedStoryPlot.getStoryName());
                    storyPlot.setIdeaText(updatedStoryPlot.getIdeaText());
                    storyPlot.setShortDescriptionText(updatedStoryPlot.getShortDescriptionText());
                    storyPlot.setSynopsisText(updatedStoryPlot.getSynopsisText());
                    storyPlot.setLongSynopsisText(updatedStoryPlot.getLongSynopsisText());
                    storyPlot.setStatus(updatedStoryPlot.getStatus());

                    return storyPlotRepository.save(updatedStoryPlot);
                })
                .orElseGet(() -> {
                    updatedStoryPlot.setId(id);
                    return storyPlotRepository.save(new StoryPlot());
                }));
    }

    public boolean deleteStoryPlot(Long id) {
        storyPlotRepository.deleteById(id);
        return true;
    }

}
