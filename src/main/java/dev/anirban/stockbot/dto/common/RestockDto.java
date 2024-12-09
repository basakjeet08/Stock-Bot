package dev.anirban.stockbot.dto.common;

import dev.anirban.stockbot.entity.Restock;
import lombok.*;

import java.sql.Timestamp;


@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestockDto {
    private Integer id;
    private Integer quantityOrdered;
    private Timestamp orderedDate;
    private Timestamp deliveredDate;
    private Integer cost;
    private Restock.Status status;
    private Integer requestedBy;
    private Integer requestedTo;
    private Integer productRequested;
}