package bkdn.afoodbe.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Instant getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Instant dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public Set<OrderFood> getOrderFoods() {
        return orderFoods;
    }

    public void setOrderFoods(Set<OrderFood> orderFoods) {
        this.orderFoods = orderFoods;
    }

    public Set<MenuFood> getMenuFoods() {
        return menuFoods;
    }

    public void setMenuFoods(Set<MenuFood> menuFoods) {
        this.menuFoods = menuFoods;
    }

}