package dev.anirban.stockbot.dto.common;

import lombok.*;


@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Integer id;
    private String name;
    private String category;
    private String description;
    private Integer price;
    private Integer quantity;
    private Integer restockThreshold;
    private Integer holdingCapacity;
    private Integer suppliedById;
}