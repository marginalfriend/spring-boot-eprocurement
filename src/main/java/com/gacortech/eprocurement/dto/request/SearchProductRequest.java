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
public class SearchProductRequest {
    @NotBlank(message = "name is required")
    private String name;

    @NotNull(message = "category is required")
    private Integer categoryId;

    private Integer page;
    private Integer size;

    private String sortBy;
    private String direction;
}
