package com.mycompany.fabricpricelist.dto;

import com.mycompany.fabricpricelist.domain.MaterialType;
import com.mycompany.fabricpricelist.domain.Product;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter @Setter
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

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // 혼용률
    private MaterialType material1;
    @Min(value = 0, message = "비율은 0~100 사이여야 합니다.")
    @Max(value = 100, message = "비율은 0~100 사이여야 합니다.")
    private Integer ratio1;

    private MaterialType material2;
    @Min(0) @Max(100)
    private Integer ratio2;

    private MaterialType material3;
    @Min(0) @Max(100)
    private Integer ratio3;

    // 혼용률 입력에 관한 검증들

    // 1. 최소 1쌍 이상 입력
    @AssertTrue(message = "최소 한 쌍 이상의 소재/비율을 입력하세요.")
    public boolean isAtLeastOne() {
        return pairExists(material1, ratio1) || pairExists(material2, ratio2) || pairExists(material3, ratio3);
    }

    private boolean pairExists(MaterialType material, Integer ratio) {
        return material != null && ratio != null;
    }

    // 2. 혼용률 합계는 100%(초과 X, 미만 X)
    @AssertTrue(message = "혼용률 합계는 반드시 100%를 이어야 합니다.")
    public boolean isTotalNotExceed() {
        return sumValidPairs() == 100;
    }

    private int sumValidPairs() {
        int s = 0;
        if (pairExists(material1, ratio1)) s += ratio1;
        if (pairExists(material2, ratio2)) s += ratio2;
        if (pairExists(material3, ratio3)) s += ratio3;
        return s;
    }

    // 3. 소재와 비율은 반드시 함께 입력해야 함
    @AssertTrue(message = "소재를 입력하면 비율도, 비율을 입력하면 소재를 입력해야 합니다.")
    public boolean isPairConsistency() {
        return pairOK(material1, ratio1) && pairOK(material2, ratio2) && pairOK(material3, ratio3);
    }

    private boolean pairOK(MaterialType material, Integer ratio) {
        if (material == null && ratio == null) return true;
        if (material != null && ratio != null && ratio >= 0 && ratio <= 100) return true;
        return false;
    }

    // 4. 같은 소재 중복 방지
    public boolean isNoDuplicateMaterials() {
        Set<MaterialType> set = new HashSet<>();
        if (material1 != null && !set.add(material1)) return false;
        if (material2 != null && !set.add(material2)) return false;
        if (material3 != null && !set.add(material3)) return false;
        return true;
    }

    // View에 출력될 문자열 생성
    public String buildCompositionString() {
        List<String> parts = new ArrayList<>();
        if (pairExists(material1, ratio1)) parts.add(fmt(material1, ratio1));
        if (pairExists(material2, ratio2)) parts.add(fmt(material2, ratio2));
        if (pairExists(material3, ratio3)) parts.add(fmt(material3, ratio3));
        return String.join(" / ", parts);
    }

    // fmt -> format의 약자
    private String fmt(MaterialType material, Integer ratio) {
        String label = switch(material) {
            case NYLON -> "Nylon";
            case POLYESTER -> "Polyester";
            case COTTON -> "Cotton";
            case SPANDEX -> "Spandex";
            case KEVLAR -> "Kevlar";
        };
        return label + " " + ratio + "%";
    }

    // 엔티티 -> DTO 변환
    public static ProductDto fromEntity(Product product) {
        ProductDto dto = new ProductDto();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setFinish(product.getFinish());
        dto.setPrice(product.getPrice());
        dto.setComposition(product.getComposition());
        dto.setCreatedAt(product.getCreatedAt());
        dto.setUpdatedAt(product.getUpdatedAt());
        return dto;
    }

}
