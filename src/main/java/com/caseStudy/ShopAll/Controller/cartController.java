package com.caseStudy.ShopAll.Controller;

import com.caseStudy.ShopAll.Repository.userRepository;
import com.caseStudy.ShopAll.Service.CartService;
import com.caseStudy.ShopAll.Service.UserService;
import com.caseStudy.ShopAll.model.Cart;
import com.caseStudy.ShopAll.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200" , allowedHeaders = "*")
public class cartController {

    @Autowired
    CartService cartS;
    @Autowired
    userRepository userR;

    @GetMapping("/addToCart/{productId}")
    public String addToCart(@PathVariable("productId") Long productId,Principal principal) {
        return cartS.addProduct(userR.findByEmail(principal.getName()).getUserId(), productId);

    }

    @GetMapping("/showCart")
    public List<Cart> showCart(Principal principal)
    {
        System.out.println(userR.findByEmail(principal.getName()).getUserId());
        Long id = userR.findByEmail(principal.getName()).getUserId();
        return  cartS.showCart(id);
    }

    @GetMapping("/clearCart")
    public String clearCart(Principal principal)
    {
        Long id = userR.findByEmail(principal.getName()).getUserId();
        return cartS.clearCart(id);
    }
    @GetMapping("/decreaseQuantity/{productId}")
    public String decQ(@PathVariable("productId") Long productId,Principal principal)
    {
        return cartS.decreaseQuant(productId,principal);

    }
    @Transactional
    @GetMapping("/deleteProduct/{productId}")
    public String deleteProduct(Principal principal,@PathVariable("productId") Long productId)
    {
        return cartS.deleteProduct(principal,productId);
    }


}
