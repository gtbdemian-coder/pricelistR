package com.mycompany.fabric_price_list.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private Long id;
    private String name;
    private String composition;
    private String finish;
    private Double price;

    // 엔티티 -> DTO 변환
    public static ProductDto fromEntity(Product product) {
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getComposition(),
                product.getFinish(),
                product.getPrice()
        );
    }

}
