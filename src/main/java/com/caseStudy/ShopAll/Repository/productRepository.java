package com.caseStudy.ShopAll.Repository;

import com.caseStudy.ShopAll.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface productRepository extends JpaRepository<Products,Long> {

    List<Products> findByCategory(String category);
    List<Products> findByCategoryAndPriceBetween(String category, double min, double max);
    List<Products> findByPriceBetween(double min, double max);
}
