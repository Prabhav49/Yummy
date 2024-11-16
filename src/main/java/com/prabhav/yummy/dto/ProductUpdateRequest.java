package com.prabhav.yummy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductUpdateRequest {
    private String name;
    private Double price;
}
