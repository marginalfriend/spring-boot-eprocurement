package com.gacortech.eprocurement.dto.entity_rep;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Order {
    private String id;
    private LocalDate orderDate;
}
