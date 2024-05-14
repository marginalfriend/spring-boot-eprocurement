package com.gacortech.eprocurement.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReportResponse {
    private Long id;
    private String productName;
    private String vendorName;
    private String purchaseDate;
    private Integer quantity;
    private Double totalPrice;

}
