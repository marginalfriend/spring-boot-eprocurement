package com.gacortech.eprocurement.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ReportResponse {
    private String id;
    private String productName;
    private String vendorName;
    private String purchaseDate;
    private Integer quantity;
    private Integer totalPrice;

}
