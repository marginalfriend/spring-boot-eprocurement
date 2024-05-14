package com.gacortech.eprocurement.entity;


import com.gacortech.eprocurement.constant.Tables;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = Tables.ORDER_DETAILS)
public class OrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Orders orders;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Products product;


    @Column(name = "quantity", nullable = false)
    private Integer quantity;
}
