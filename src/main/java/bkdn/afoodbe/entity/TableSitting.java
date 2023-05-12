package bkdn.afoodbe.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "table_sitting")
public class TableSitting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "in_time", nullable = false)
    private Instant inTime;

    @Column(name = "out_time", nullable = false)
    private Instant outTime;

    @Column(name = "num_person_sitting", nullable = false)
    private Integer numPersonSitting;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "table_id", nullable = false)
    private TableRestaurant table;

    @OneToMany(mappedBy = "tableSitting")
    private Set<Order> orders = new LinkedHashSet<>();

}