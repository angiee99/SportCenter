package com.sportcenterplatform.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Represents a sport event entity.
 * This class defines the structure and behavior of sport event objects.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "sports_events")
public class SportsEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "capacity")
    private Integer capacity;

    @Column(name = "isAvailable")
    private Boolean isAvailable;

    @OneToMany(mappedBy = "sportsEvent", cascade = CascadeType.ALL)
    private List<Schedule> schedules;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trainer_id", foreignKey = @ForeignKey(name = "FK_sports_events_trainers"))
    private User trainer;

    public SportsEvent(String description, Boolean isAvailable, Integer capacity, User trainer) {
        this.description = description;
        this.isAvailable = isAvailable;
        this.capacity = capacity;
        this.trainer = trainer;
    }

}
