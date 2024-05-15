package com.gacortech.eprocurement.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderDetailRequest {
    private Integer productSupplyId;

    @NotBlank(message = "Quantity is required")
    private Integer quantity;

    @NotBlank(message = "Price is required")
    private Integer price;
}
