package com.caseStudy.ShopAll.Repository;

import com.caseStudy.ShopAll.model.OrderHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface orderRepo extends JpaRepository<OrderHistory,Long> {
    List<OrderHistory> findByUserId(Long userId);
}
