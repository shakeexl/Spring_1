package ru.geekbrains.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.repository.Product;
import ru.geekbrains.service.ProductService;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String showAll(Model model) {
        model.addAttribute("productList", productService.getProductList());
        model.addAttribute("currentPage", "products");
        return "products";
    }

    @GetMapping("/edit")
    public String editProduct(@RequestParam(required = false) Long id,
                              @RequestParam(required = false) Boolean view,
                              Model model) {
        Product product;
        if (id == null) {
            product = new Product(null, null, null);
        } else {
            product = productService.getProductById(id);
        }
        model.addAttribute("product", product);
        model.addAttribute("disabled", view);
        model.addAttribute("currentPage", "products");

        return "form";
    }

    @PostMapping("/edit/save")
    public String mergeProduct(@ModelAttribute Product product) {
        productService.saveOrUpdate(product);
        return "redirect:/products";
    }

    @GetMapping("/delete")
    public String deleteProduct(@RequestParam Long id, Model model) {
        productService.deleteById(id);
        model.addAttribute("productList", productService.getProductList());
        return "redirect:/products";
    }
}
