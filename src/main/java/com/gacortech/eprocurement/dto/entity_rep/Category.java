package com.gacortech.eprocurement.dto.entity_rep;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Category {
    private Integer id;
    private String name;
}
