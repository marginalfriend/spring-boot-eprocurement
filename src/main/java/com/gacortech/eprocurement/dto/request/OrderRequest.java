package com.gacortech.eprocurement.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderRequest {
    private List<OrderDetailRequest> orderDetails;
    private String productSupplyIds;

    public String getProductSupplyIds() {
        return productSupplyIds;
    }

    public void setProductSupplyIds(String productSupplyIds) {
        this.productSupplyIds = productSupplyIds;
    }
}

//  [
//        {
//          productSupplyId : *****
//          quantity : *****
//        },
//        {
//          productSupplyId : *****
//          quantity : *****
//        },
//        {
//          productSupplyId : *****
//          quantity : *****
//        }
//  ]