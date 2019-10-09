package com.caseStudy.ShopAll.Service;

import com.caseStudy.ShopAll.Repository.productRepository;
import com.caseStudy.ShopAll.model.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductService {


    @Autowired
    productRepository prodRepo;


    public List<Products> getProductList() {
        System.out.println(prodRepo.findAll());
        return prodRepo.findAll();
    }

    public Products addProduct(Products prod) {
        return prodRepo.save(prod);
    }
public String deleteProduct(long id)
{
    prodRepo.deleteById(id);
    return "done";
}

public List<Products> findProductsByCategory(String category)
{
   return  prodRepo.findByCategory(category);
}
public Products getProductById(Long id)
{
    return prodRepo.findById(id).orElse(new Products());
}
public List<Products> findByPriceAndCat(String category, double min, double max)
{
    return prodRepo.findByCategoryAndPriceBetween(category,min,max);
}
public List<Products> findByPriceOfAll(double min, double max)
{
    return prodRepo.findByPriceBetween(min,max);
}
}
