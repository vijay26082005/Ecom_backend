package com.example.Ecom_BackEnd;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ShopController {

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private CartService cartService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("products", productRepo.findAll());
        return "products";
    }

    @GetMapping("/add-to-cart/{id}")
    public String addToCart(@PathVariable Long id) {
        Product product = productRepo.findById(id).orElseThrow();
        cartService.addToCart(product);
        return "redirect:/";
    }

    @GetMapping("/cart")
    public String viewCart(Model model) {
        model.addAttribute("items", cartService.getCartItems());
        model.addAttribute("total", cartService.getTotal());
        return "cart";
    }

    @GetMapping("/checkout")
    public String checkout() {
        cartService.clearCart();
        return "checkout";
    }
}
