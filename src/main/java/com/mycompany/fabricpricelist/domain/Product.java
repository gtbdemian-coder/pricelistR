package com.mycompany.fabricpricelist.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
public class Product extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String composition;
    private String finish;
    private Double price;


    public Product(String name, String composition, String finish, Double price) {
        this.name = name;
        this.composition = composition;
        this.finish = finish;
        this.price = price;
    }

    public void update(String name, String composition, String finish, Double price) {
        this.name = name;
        this.composition = composition;
        this.finish = finish;
        this.price = price;
    }
}
