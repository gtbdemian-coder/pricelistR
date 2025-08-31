package com.mycompany.fabric_price_list.product;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Setter @Getter
public class Product {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String composition;
    private String finish;
    private Double price;

}
