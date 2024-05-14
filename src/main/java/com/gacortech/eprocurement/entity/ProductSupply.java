package com.gacortech.eprocurement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product_supply")
public class ProductSupply {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @JoinColumn(name = "product_id", nullable = false)
    private Products productId;

    @ManyToOne
    @JoinColumn(name = "vendor_id", nullable = false)
    private Vendors vendorId;

    @Column(name = "stock")
    private Integer stock;

    @Column(name = "price")
    private Integer price;
}
