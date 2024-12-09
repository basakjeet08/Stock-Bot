package dev.anirban.stockbot.entity;

import dev.anirban.stockbot.dto.common.RestockDto;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "RESTOCK")
public class Restock {

    public enum Status {
        REQUESTED, WITHDRAWN, DELIVERED
    }

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "quantity_ordered", nullable = false)
    private Integer quantityOrdered;

    @Column(name = "ordered_date", nullable = false)
    private Timestamp orderedDate;

    @Column(name = "delivered_date")
    private Timestamp deliveredDate;

    @Column(name = "cost", nullable = false)
    private Integer cost;

    @Column(name = "status", nullable = false)
    private Restock.Status status;

    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;

    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt;

    @ManyToOne(
            cascade = {CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST},
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "requested_by_id", nullable = false)
    private Employee requestedBy;

    @ManyToOne(
            cascade = {CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST},
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "requested_to_id", nullable = false)
    private Supplier requestedTo;

    @ManyToOne(
            cascade = {CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST},
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "product_requested_id", nullable = false)
    private Product productRequested;

    public RestockDto toRestockDto() {
        return RestockDto
                .builder()
                .id(id)
                .quantityOrdered(quantityOrdered)
                .orderedDate(orderedDate)
                .deliveredDate(deliveredDate)
                .cost(cost)
                .status(status)
                .requestedBy(requestedBy.getId())
                .requestedTo(requestedTo.getId())
                .productRequested(productRequested.getId())
                .build();
    }
}