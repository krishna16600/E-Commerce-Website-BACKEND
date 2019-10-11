package com.caseStudy.ShopAll.Service;

import com.caseStudy.ShopAll.Repository.cartRepository;
import com.caseStudy.ShopAll.Repository.productRepository;
import com.caseStudy.ShopAll.Repository.userRepository;
import com.caseStudy.ShopAll.model.Cart;
import com.caseStudy.ShopAll.model.Products;
import com.caseStudy.ShopAll.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.List;

@Component
public class CartService {

    @Autowired
    private ProductService prodS;
    @Autowired
    private UserService userS;
    @Autowired
    private cartRepository cart;
    public String addProduct(Long userid, Long productid)
    {
        Products prod = prodS.getProductById(productid);
        Users user = userS.findByUserId(userid);

        if(cart.findByUsersAndProducts(user,prod)!=null)
        {
            Cart car = cart.findByUsersAndProducts(user,prod);
            car.setQuantity(car.getQuantity()+1);
            cart.save(car);
        }else{
            Cart car = new Cart(user,prod,1);
            cart.save(car);
        }
        System.out.println("added");
        return "\"Added\"";
    }

    public List<Cart> showCart(Long userId)
    {
        Users u = userS.findByUserId(userId);
        System.out.println(u.getName());
        return cart.findByUsers(u);
    }

    public String clearCart(Long userId)
    {
        Users u = userS.findByUserId(userId);
        List<Cart> cartItems = cart.findByUsers(u);
        for(Cart c: cartItems)
        {
            cart.deleteById(c.getId());
        }
        return "\"Cart cleared\"";
    }
    public String deleteProduct(Principal principal, Long productId)
    {

            Products prod = prodS.getProductById(productId);
            Users user = userS.getUserByEmail(principal.getName());
            cart.deleteByUsersAndProducts(user,prod);
             return "\"Deleted Product\"";

    }

    public String decreaseQuant(Long productId,Principal principal)
    {
        Products prod = prodS.getProductById(productId);
       Users user = userS.getUserByEmail(principal.getName());

       if(cart.findByUsersAndProducts(user,prod)!=null)
       {
           Cart car = cart.findByUsersAndProducts(user,prod);
           if(car.getQuantity()>=2)
           {
               car.setQuantity(car.getQuantity()-1);
               cart.save(car);
           }
           else if(car.getQuantity()==1){
               deleteProduct(principal,productId);
           }
       }
        System.out.println("decreased");
       return "\"Decreased\"";

    }
}
