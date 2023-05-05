package bkdn.afoodbe.entity;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "`order`")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "order_in_time", nullable = false)
    private Instant orderInTime;

    @Column(name = "order_out_time", nullable = false)
    private Instant orderOutTime;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "table_sitting_id", nullable = false)
    private TableSitting tableSitting;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "staff_id", nullable = false)
    private Staff staff;

    @Column(name = "order_status", nullable = false, length = 45)
    private String orderStatus;

    @OneToMany(mappedBy = "order")
    private Set<OrderFood> orderFoods = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Instant getOrderInTime() {
        return orderInTime;
    }

    public void setOrderInTime(Instant orderInTime) {
        this.orderInTime = orderInTime;
    }

    public Instant getOrderOutTime() {
        return orderOutTime;
    }

    public void setOrderOutTime(Instant orderOutTime) {
        this.orderOutTime = orderOutTime;
    }

    public TableSitting getTableSitting() {
        return tableSitting;
    }

    public void setTableSitting(TableSitting tableSitting) {
        this.tableSitting = tableSitting;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Set<OrderFood> getOrderFoods() {
        return orderFoods;
    }

    public void setOrderFoods(Set<OrderFood> orderFoods) {
        this.orderFoods = orderFoods;
    }

}