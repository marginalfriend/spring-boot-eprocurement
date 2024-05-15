package com.gacortech.eprocurement.dto.entity_rep;

import com.gacortech.eprocurement.entity.Categories;
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
public class Product {
    private String id;
    @NotBlank(message = "name is required")
    private String name;
    @NotNull(message = "category is required")
    private Integer categoryId;
}
