package bkdn.afoodbe.entity;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getAreaDescription() {
        return areaDescription;
    }

    public void setAreaDescription(String areaDescription) {
        this.areaDescription = areaDescription;
    }

    public Set<TableRestaurant> getTableRestaurants() {
        return tableRestaurants;
    }

    public void setTableRestaurants(Set<TableRestaurant> tableRestaurants) {
        this.tableRestaurants = tableRestaurants;
    }

}