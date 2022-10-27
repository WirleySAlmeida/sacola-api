package me.dio.sacolaapi.resource.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ItemDto {
    private Long productId;
    private int quantity;
    private Long bagId;
}
