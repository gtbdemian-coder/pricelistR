package com.mycompany.fabric_price_list.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;

    // 기능1: 제품 등록
    @Transactional
    public void registerProduct(ProductDto productDto) {
        Product p = new Product();
        p.setName(productDto.getName());
        p.setComposition(productDto.getComposition());
        p.setFinish(productDto.getFinish());
        p.setPrice(productDto.getPrice());
        repository.save(p);
    }

    // 기능2: 제품 수정
    @Transactional
    public void updateProduct(ProductDto productDto) {
        Product p = repository.findById(productDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 제품"));
        p.setName(productDto.getName());
        p.setComposition(productDto.getComposition());
        p.setFinish(productDto.getFinish());
        p.setPrice(productDto.getPrice());
    }

    // 기능3: 가격표 조회
    @Transactional(readOnly = true)
    public List<ProductDto> getPriceList() {
        return repository.findAll()
                .stream()
                .map(ProductDto::fromEntity)
                .toList();
    }
}
