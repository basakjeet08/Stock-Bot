package dev.anirban.stockbot.entity;


import dev.anirban.stockbot.dto.common.ProductDto;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.Set;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PRODUCT")
public class Product {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "price", nullable = false)
    private Integer price;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "restock_threshold", nullable = false)
    private Integer restockThreshold;

    @Column(name = "holding_capacity", nullable = false)
    private Integer holdingCapacity;

    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;

    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt;

    @ManyToOne(
            cascade = {CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST},
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "supplied_by_id")
    private Supplier suppliedBy;

    @OneToMany(
            mappedBy = "productRequested",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST}
    )
    private Set<Restock> restockList;

    public void addRestock(Restock restock) {
        restockList.add(restock);
        restock.setProductRequested(this);
    }

    public ProductDto toProductDto() {
        return ProductDto
                .builder()
                .id(id)
                .name(name)
                .category(category)
                .description(description)
                .price(price)
                .quantity(quantity)
                .restockThreshold(restockThreshold)
                .holdingCapacity(holdingCapacity)
                .suppliedById(suppliedBy.getId())
                .build();
    }
}