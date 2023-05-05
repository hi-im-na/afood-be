package bkdn.afoodbe.entity;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TableArea getTableArea() {
        return tableArea;
    }

    public void setTableArea(TableArea tableArea) {
        this.tableArea = tableArea;
    }

    public Integer getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(Integer maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public Staff getServingStaff() {
        return servingStaff;
    }

    public void setServingStaff(Staff servingStaff) {
        this.servingStaff = servingStaff;
    }

    public String getTableStatus() {
        return tableStatus;
    }

    public void setTableStatus(String tableStatus) {
        this.tableStatus = tableStatus;
    }

    public Set<TableSitting> getTableSittings() {
        return tableSittings;
    }

    public void setTableSittings(Set<TableSitting> tableSittings) {
        this.tableSittings = tableSittings;
    }

}