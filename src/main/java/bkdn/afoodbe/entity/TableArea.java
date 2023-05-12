package bkdn.afoodbe.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "table_area")
public class TableArea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "area_name")
    private String areaName;

    @Lob
    @Column(name = "area_description")
    private String areaDescription;

    @OneToMany(mappedBy = "tableArea")
    private Set<TableRestaurant> tableRestaurants = new LinkedHashSet<>();

}