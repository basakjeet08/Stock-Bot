package dev.anirban.stockbot.entity;


import dev.anirban.stockbot.dto.common.SupplierDto;
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
@Table(name = "SUPPLIER")
public class Supplier {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "contact_no", nullable = false)
    private String contactNo;

    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;

    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt;

    @OneToMany(
            mappedBy = "suppliedBy",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST}
    )
    private Set<Product> productList;

    public void addProduct(Product product) {
        productList.add(product);
        product.setSuppliedBy(this);
    }

    public SupplierDto toSupplierDto(){
        return SupplierDto
                .builder()
                .id(id)
                .name(name)
                .address(address)
                .contactNo(contactNo)
                .build();
    }
}