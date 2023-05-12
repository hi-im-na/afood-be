package bkdn.afoodbe.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
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

    @Column(name = "role", nullable = false, length = 45)
    private String role;

    @Column(name = "full_name", nullable = false, length = 45)
    private String fullName;

    @Column(name = "phone_number", length = 45)
    private String phoneNumber;

    @Column(name = "citizen_id", length = 45)
    private String citizenId;

    @OneToMany(mappedBy = "staff")
    private Set<Order> orders = new LinkedHashSet<>();

    @OneToMany(mappedBy = "servingStaff")
    private Set<TableRestaurant> tableRestaurants = new LinkedHashSet<>();

    @OneToMany(mappedBy = "staff")
    private Set<OrderDelivery> orderDeliveries = new LinkedHashSet<>();

}