package com.gacortech.eprocurement.dto.entity_rep;


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
    private String name;
    List<Product> products;
}
