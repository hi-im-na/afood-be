package bkdn.afoodbe.entity;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Instant getInTime() {
        return inTime;
    }

    public void setInTime(Instant inTime) {
        this.inTime = inTime;
    }

    public Instant getOutTime() {
        return outTime;
    }

    public void setOutTime(Instant outTime) {
        this.outTime = outTime;
    }

    public Integer getNumPersonSitting() {
        return numPersonSitting;
    }

    public void setNumPersonSitting(Integer numPersonSitting) {
        this.numPersonSitting = numPersonSitting;
    }

    public TableRestaurant getTable() {
        return table;
    }

    public void setTable(TableRestaurant table) {
        this.table = table;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

}