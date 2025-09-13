package com.mycompany.fabricpricelist.controller;

import com.mycompany.fabricpricelist.dto.ProductDto;
import com.mycompany.fabricpricelist.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    /**
     * METHOD: GET
     * URL: /prices
     * 가격표 화면 조회
     */
    @GetMapping("/prices")
    public String viewPriceList(Model model) {
        List<ProductDto> products = service.getPriceList();
        model.addAttribute("products", products);
        return "prices";
    }

    /**
     * METHOD: GET
     * URL: /prices/register
     * 제품 등록 화면 조회
     */
    @GetMapping("/prices/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("form", new ProductDto());
        return "price_register";
    }

    /**
     * METHOD: POST
     * URL: /prices/register
     * 제품 등록
     */
    @PostMapping("/prices/register")
    public String registerProduct(@Valid @ModelAttribute("form") ProductDto productDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "price_register";
        }
        service.registerProduct(productDto);
        redirectAttributes.addFlashAttribute("message", "제품이 등록되었습니다.");
        return "redirect:/prices";
    }

}
