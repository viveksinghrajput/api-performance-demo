package com.vivek.facade;

import com.vivek.dto.ProductDetailDTO;
import com.vivek.entity.Inventory;
import com.vivek.entity.Price;
import com.vivek.entity.Product;
import com.vivek.service.InventoryService;
import com.vivek.service.PriceService;
import com.vivek.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
@Slf4j
public class ProductASyncFacade {

    @Autowired
    private ProductService productService;
    @Autowired
    private InventoryService inventoryService;
    @Autowired
    private PriceService priceService;

    public CompletableFuture<Product> getProductById(long productId) {
        System.out.println("getProductById   "+Thread.currentThread().getName());
        CompletableFuture<Product> productCompletableFuture = CompletableFuture
                .supplyAsync(() -> {
                    System.out.println("getProductById  Async " + Thread.currentThread().getName());
                    return productService.findById(productId);
                });
    return productCompletableFuture;
    }

    public CompletableFuture<Price> getPriceByProductById(long productId) {
        System.out.println("getPriceByProductById   "+Thread.currentThread().getName());

        CompletableFuture<Price> priceCompletableFuture = CompletableFuture
                .supplyAsync(() -> {
                    System.out.println("getPriceByProductById  Async " + Thread.currentThread().getName());
                    return priceService.getPriceByProductId(productId);
                });
        return priceCompletableFuture;
    }

    public CompletableFuture<Inventory> getInventoryByProductId(long productId) {
        System.out.println("getInventoryByProductId   "+Thread.currentThread().getName());
        CompletableFuture<Inventory> inventoryCompletableFuture = CompletableFuture
                .supplyAsync(() -> {
                    System.out.println("getInventoryByProductId  Async " + Thread.currentThread().getName());
                    return inventoryService.getInventoryByProductId(productId);
                });
        return inventoryCompletableFuture;
    }


    public ProductDetailDTO getProductDetails(long productId) {

        //fetch all async
        CompletableFuture<Product> productFuture = getProductById(productId);
        CompletableFuture<Price> priceFuture = getPriceByProductById(productId);
        CompletableFuture<Inventory> inventoryFuture = getInventoryByProductId(productId);

        //wait for all futures to complete
        CompletableFuture.allOf(priceFuture, productFuture, inventoryFuture);

        //combine the result
        Product product = productFuture.join();
        Price price = priceFuture.join();
        Inventory inventory = inventoryFuture.join();

        //build and return

        return new ProductDetailDTO(productId, product.getCategory().getName(),
                product.getName(), product.getDescription(),
                inventory.getAvailableQuantity(), price.getPrice(),
                inventory.getStatus());

    }
}