package com.caseStudy.ShopAll.Controller;

import com.caseStudy.ShopAll.Repository.productRepository;
import com.caseStudy.ShopAll.Service.ProductService;
import com.caseStudy.ShopAll.model.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:4200" , allowedHeaders = "*")
public class ProductController {

    @Autowired
    ProductService prodS;

    @GetMapping("/allProducts")
    public List<Products> getAllProducts()
    {
        List<Products> list = prodS.getProductList();
        return list;
    }

    @PostMapping("/enterProducts")
    public Products saveProduct(@RequestBody Products prod)
    {
        return prodS.addProduct(prod);
    }

    @PostMapping("/editProducts")
    public Products editPr(@RequestBody Products prod)
    {
        System.out.println(prod.toString());
        System.out.println("After edit" + prodS.editPro(prod).toString());
        return prodS.editPro(prod);

    }

    @DeleteMapping("/deleteProducts/{id}")
    public String deleteProduct(@PathVariable("id") Long id)
    {
        return prodS.deleteProduct(id);
    }

    @GetMapping("/allProducts/{category}")
    public List<Products> getProductsByCategory(@PathVariable("category") String category)
    {
       List<Products> list =  prodS.findProductsByCategory(category);
        System.out.println(list.toString() +" " + list.size());
        return list;
    }
    @GetMapping("/products/{productId}")
    public Products getProdById(@PathVariable("productId")Long id)
    {
        System.out.println("id: "+ id);
    return prodS.getProductById(id);
    }
    @GetMapping("allProducts/{category}/{min}/{max}")
    public List<Products> getByPriceAndCateg(@PathVariable("category") String categ, @PathVariable("min") double min, @PathVariable("max") double max)
    {
        List<Products> list = prodS.findByPriceAndCat(categ, min, max);
        System.out.println("list is: " + list);
        return list;
    }
    @GetMapping("allProducts/{min}/{max}")
    public List<Products> getByPrice(@PathVariable("min") double min, @PathVariable("max")double max)
    {
        return prodS.findByPriceOfAll(min,max);
    }

    @GetMapping("/search/{searchedItem}")
    public Set<Products> searchItem(@PathVariable("searchedItem") String searchedItem) {
        Set<Products> prod = prodS.getSearchedData(searchedItem);
        for (int i = 0; i < prod.size(); i++) {
            System.out.println(prod);
        }
        return prod;
    }
}
