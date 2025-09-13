package com.mycompany.fabricpricelist.dto;

import com.mycompany.fabricpricelist.domain.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.springframework.format.annotation.NumberFormat;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private Long id;
    @NotBlank(message = "제품명을 입력하세요")
    private String name;

    private String composition;
    private String finish;

    @NotNull(message = "가격을 입력하세요")
    @Positive(message = "가격은 0보다 커야합니다.")
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
