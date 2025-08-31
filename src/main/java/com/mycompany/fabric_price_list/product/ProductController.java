package com.mycompany.fabric_price_list.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductRepository repository;
    private final ProductService service;

    @GetMapping("/prices")
    public String viewPriceList(Model model) {
        List<Product> products = repository.findAll();
        model.addAttribute("products", products);
        return "prices";
    }

    @GetMapping("/prices/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("form", new ProductDto());
        return "price_register";
    }

    @PostMapping("/prices/register")
    public String registerProduct(@ModelAttribute("form") ProductDto productDto) {
        service.registerProduct(productDto);
        return "redirect:/prices";
    }

}
