package bkdn.afoodbe.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "table_restaurant")
public class TableRestaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "table_area_id", nullable = false)
    private TableArea tableArea;

    @Column(name = "max_capacity", nullable = false)
    private Integer maxCapacity;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "serving_staff_id", nullable = false)
    private Staff servingStaff;

    @Column(name = "table_status", nullable = false, length = 45)
    private String tableStatus;

    @OneToMany(mappedBy = "table")
    private Set<TableSitting> tableSittings = new LinkedHashSet<>();

}