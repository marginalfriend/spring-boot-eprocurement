package com.gacortech.eprocurement.entity;

import com.gacortech.eprocurement.constant.Tables;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = Tables.PRODUCT_SUPPLY)
public class ProductSupplies {
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
