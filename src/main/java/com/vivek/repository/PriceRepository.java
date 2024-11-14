package com.vivek.repository;

import com.vivek.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository extends JpaRepository<Price,Long> {
    Price findByProductId(Long productId);
}
