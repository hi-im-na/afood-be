package bkdn.afoodbe.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "food")
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Lob
    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "cost", nullable = false, precision = 10, scale = 2)
    private BigDecimal cost;

    @Column(name = "image")
    private String image;

    @Column(name = "date_updated", nullable = false)
    private Instant dateUpdated;

    @OneToMany(mappedBy = "food")
    private Set<OrderFood> orderFoods = new LinkedHashSet<>();

    @OneToMany(mappedBy = "food")
    private Set<MenuFood> menuFoods = new LinkedHashSet<>();

}