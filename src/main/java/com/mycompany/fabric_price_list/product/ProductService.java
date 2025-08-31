package com.mycompany.fabric_price_list.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;

    /**
     * 제품 등록
     */
    @Transactional
    public void registerProduct(ProductDto productDto) {
        Product p = new Product();
        p.setName(productDto.getName());
        p.setComposition(productDto.getComposition());
        p.setFinish(productDto.getFinish());
        p.setPrice(productDto.getPrice());
        repository.save(p);
    }

    /**
     * 제품 수정
     */
    @Transactional
    public void updateProduct(Product product) {
        repository.save(product);
    }

}
