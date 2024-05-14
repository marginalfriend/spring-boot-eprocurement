package com.gacortech.eprocurement.dto.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Report {
    private Long id;
    private String productName;
    private String vendorName;
    private String purchaseDate;
    private Integer quantity;
    private Double totalPrice;

}
