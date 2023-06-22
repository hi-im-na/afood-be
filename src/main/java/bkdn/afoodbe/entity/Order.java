package bkdn.afoodbe.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@Entity
@Table(name = "`order`")
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "order_in_time", nullable = false)
    private Instant orderInTime;

    @Column(name = "order_out_time", nullable = true)
    private Instant orderOutTime;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "table_sitting_id", nullable = false)
    private TableSitting tableSitting;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "staff_id", nullable = false)
    private Staff staff;

    @Column(name = "order_status", nullable = false, length = 45)
    private String orderStatus;

    @Column(name = "total_cost", precision = 10, scale = 2)
    private BigDecimal totalCost;

    @OneToMany(mappedBy = "order")
    private Set<OrderFood> orderFoods = new LinkedHashSet<>();

}
