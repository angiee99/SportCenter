package com.sportcenterplatform.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * Represents a schedule entity.
 * This class defines the structure and behavior of schedule objects.
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "schedules")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @Column(name = "signed_up_count")
    private Integer signedUpCount;

    @Column(name = "capacity")
    private Integer capacity;

    @OneToMany(mappedBy = "schedule")
    @ToString.Exclude
    private List<EventSignup> eventSignups;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sports_event_id", foreignKey = @ForeignKey(name = "FK_schedules_sports_events"))
    @ToString.Exclude
    private SportsEvent sportsEvent;

    public Schedule(LocalDateTime startTime, LocalDateTime endTime, SportsEvent sportsEvent, Integer capacity) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.sportsEvent = sportsEvent;
        this.capacity = capacity;
        signedUpCount = 0;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Schedule schedule = (Schedule) o;
        return getId() != null && Objects.equals(getId(), schedule.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
