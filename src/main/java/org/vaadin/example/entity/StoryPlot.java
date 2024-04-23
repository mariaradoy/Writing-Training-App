package org.vaadin.example.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "story_plots")
public class StoryPlot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    private String storyName;
    private String ideaText;
    private String shortDescriptionText;
    private String synopsisText;
    private String longSynopsisText;
    private Status status;


}
