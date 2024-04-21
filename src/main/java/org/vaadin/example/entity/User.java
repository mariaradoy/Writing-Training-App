package org.vaadin.example.entity;

import jakarta.persistence.*;
import lombok.*;


@Data
@Entity
@NoArgsConstructor
@Table(name = "users")
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;




}
