package com.gacortech.eprocurement.dto.entity_rep;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Category {
    private Integer id;
    @NotBlank(message = "name is required")
    private String name;
}
