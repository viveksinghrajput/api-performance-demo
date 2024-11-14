package com.vivek.service.impl;

import com.vivek.entity.Inventory;
import com.vivek.repository.InventoryRepository;
import com.vivek.service.InventoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private  InventoryRepository inventoryRepository;


    @Override
    public Inventory getInventoryByProductId(Long productId) {
        log.info("Getting inventory for the productId {}", productId);
        addDelay();
        return inventoryRepository.findByProductId(productId);
    }

    private void addDelay() {
        try {
            // adding 2s delay
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}