package com.caseStudy.ShopAll.Service;

import com.caseStudy.ShopAll.Repository.productRepository;
import com.caseStudy.ShopAll.model.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
public Products editPro(Products prod)
{
    Products pr = prodRepo.findByProductId(prod.getProductId());
    pr.setCategory(prod.getCategory());
    pr.setDetails(prod.getDetails());
    pr.setName(prod.getName());
    pr.setPrice(prod.getPrice());
    pr.setImage(prod.getImage());
    prodRepo.saveAndFlush(pr);
    return pr;
}
    public Set<Products> getSearchedData(String searchedItem) {
        List<Products> productsList = prodRepo.findAll();
        Set<Products> result = new HashSet<>();

        for(int i=0; i<productsList.size(); i++) {
            if(productsList.get(i).getName().toLowerCase().contains(searchedItem.toLowerCase()) ||
                    productsList.get(i).getCategory().toLowerCase().contains(searchedItem.toLowerCase()) ||
                    productsList.get(i).getDetails().toLowerCase().contains(searchedItem.toLowerCase())) {

                result.add(productsList.get(i));
            }
        }
        //System.out.println("Search result yha tak");
        return result;
    }

}
