package com.vivek.service;

import com.vivek.entity.Price;

public interface PriceService {
    Price getPriceByProductId(Long productId);
}
