package com.caseStudy.ShopAll.Repository;

import com.caseStudy.ShopAll.model.Cart;
import com.caseStudy.ShopAll.model.Products;
import com.caseStudy.ShopAll.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface cartRepository extends JpaRepository<Cart,Long> {
    public Cart findByUsersAndProducts(Users user ,Products product);
    public Cart findByProducts(Products prod);
    public List<Cart> findByUsers(Users user);
       String deleteByUsersAndProducts(Users user, Products prod);
}
