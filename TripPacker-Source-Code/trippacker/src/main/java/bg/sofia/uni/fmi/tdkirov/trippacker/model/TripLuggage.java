package bg.sofia.uni.fmi.tdkirov.trippacker.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "trip_luggage")
public class TripLuggage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<PackingGroup> packingGroups;

    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Setter(AccessLevel.NONE)
    private Set<PackingItem> packingItems;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by_id", nullable = false)
    private User createdBy;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public TripLuggage(Long id) {
        this.id = id;
    }

    public TripLuggage(String name, User createdBy) {
        this.name = name;
        this.createdBy = createdBy;

        this.packingGroups = new LinkedHashSet<>();
        this.packingItems = new LinkedHashSet<>();

        this.createdAt = LocalDateTime.now();
    }

    public void addPackingGroup(PackingGroup packingGroupToAdd) {
        this.packingGroups.add(packingGroupToAdd);
        packingGroupToAdd.setTrip(this);
    }

    public void addPackingItem(PackingItem packingItemToAdd) {
        this.packingItems.add(packingItemToAdd);
        packingItemToAdd.setTrip(this);
    }
}
