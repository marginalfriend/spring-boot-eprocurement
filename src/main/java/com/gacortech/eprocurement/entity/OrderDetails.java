package com.gacortech.eprocurement.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.gacortech.eprocurement.constant.Tables;
import jakarta.persistence.*;
import lombok.*;

import java.nio.CharBuffer;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = Tables.ORDER_DETAILS)
public class OrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonBackReference
    private Orders orders;

    @ManyToOne
    @JoinColumn(name = "productSupply_id")
    @JsonBackReference
    private ProductSupplies productSupplies;


    @Column(name = "quantity", nullable = false)
    private Integer quantity;


}
