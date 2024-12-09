package dev.anirban.stockbot.dto.common;

import lombok.*;


@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SupplierDto {
    private Integer id;
    private String name;
    private String address;
    private String contactNo;
}