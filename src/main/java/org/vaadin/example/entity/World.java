package org.vaadin.example.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "worlds")
public class World {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    private String worldName;
    private String historyText;
    private String economyText;
    private String politicsText;
    private String religionText;
    private String geographyText;
    private String cultureText;
    private String inhabitantsText;
    private String magicText;

}
