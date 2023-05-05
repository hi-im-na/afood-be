package bkdn.afoodbe.entity;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "staff")
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "username", nullable = false, length = 45)
    private String username;

    @Column(name = "password", nullable = false, length = 45)
    private String password;

    @OneToMany(mappedBy = "staff")
    private Set<Order> orders = new LinkedHashSet<>();

    @OneToMany(mappedBy = "servingStaff")
    private Set<TableRestaurant> tableRestaurants = new LinkedHashSet<>();

    @OneToMany(mappedBy = "staff")
    private Set<OrderDelivery> orderDeliveries = new LinkedHashSet<>();

    @OneToMany(mappedBy = "staff")
    private Set<StaffInfo> staffInfos = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public Set<TableRestaurant> getTableRestaurants() {
        return tableRestaurants;
    }

    public void setTableRestaurants(Set<TableRestaurant> tableRestaurants) {
        this.tableRestaurants = tableRestaurants;
    }

    public Set<OrderDelivery> getOrderDeliveries() {
        return orderDeliveries;
    }

    public void setOrderDeliveries(Set<OrderDelivery> orderDeliveries) {
        this.orderDeliveries = orderDeliveries;
    }

    public Set<StaffInfo> getStaffInfos() {
        return staffInfos;
    }

    public void setStaffInfos(Set<StaffInfo> staffInfos) {
        this.staffInfos = staffInfos;
    }

}