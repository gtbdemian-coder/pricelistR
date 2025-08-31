package com.mycompany.fabric_price_list.product;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class ProductDto {

    private String name;
    private String composition;
    private String finish;
    private Double price;

}
