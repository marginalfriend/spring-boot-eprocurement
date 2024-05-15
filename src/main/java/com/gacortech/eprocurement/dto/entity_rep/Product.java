package com.gacortech.eprocurement.dto.entity_rep;

import com.gacortech.eprocurement.entity.Categories;
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
    private String name;
    private Integer categoryId;
}
