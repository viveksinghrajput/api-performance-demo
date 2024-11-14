package com.vivek.service.impl;

import com.vivek.entity.Price;
import com.vivek.repository.PriceRepository;
import com.vivek.service.PriceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PriceServiceImpl implements PriceService {

    @Autowired
    private  PriceRepository priceRepository;



    @Override
    public Price getPriceByProductId(Long productId) {
        log.info("Getting price for the productId {}", productId);
        addDelay();
        return priceRepository.findByProductId(productId);
    }

    private void addDelay() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}