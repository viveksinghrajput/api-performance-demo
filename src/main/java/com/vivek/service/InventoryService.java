package com.vivek.service;

import com.vivek.entity.Inventory;

public interface InventoryService {
    Inventory getInventoryByProductId(Long productId);
}
