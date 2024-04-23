package org.vaadin.example.entity;

import com.vaadin.flow.component.Text;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "exercises")
public class Exercise {
    @Id
    @GeneratedValue
    private Long id;
    private String text;
    private String type;

    public Exercise(String text, String type) {
        this.text = text;
        this.type = type;
    }

}
