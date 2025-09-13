package com.mycompany.fabricpricelist.service;

import com.mycompany.fabricpricelist.domain.Product;
import com.mycompany.fabricpricelist.dto.ProductDto;
import com.mycompany.fabricpricelist.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository repository;

    // 기능1: 제품 등록
    @Transactional
    public ProductDto registerProduct(ProductDto productDto) {
        log.info("REGISTER TRY: {}", productDto);
        Product p = new Product(productDto.getName(), productDto.getComposition(), productDto.getFinish(), productDto.getPrice());
        Product savedProduct = repository.save(p);
        log.info("REGISTER OK: id={}", savedProduct.getId());
        return ProductDto.fromEntity(savedProduct);
    }

    // 기능2: 제품 수정
    @Transactional
    public ProductDto updateProduct(ProductDto productDto) {
        Product p = repository.findById(productDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 제품입니다."));
        p.update(productDto.getName(), productDto.getComposition(), productDto.getFinish(), productDto.getPrice());
        return ProductDto.fromEntity(p);
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
