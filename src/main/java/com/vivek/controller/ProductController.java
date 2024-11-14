package com.vivek.controller;

import com.vivek.dto.ProductDetailDTO;
import com.vivek.facade.ProductASyncFacade;
import com.vivek.facade.ProductSyncFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
@Slf4j
public class ProductController {

    @Autowired
    private ProductASyncFacade productASyncFacade;
    @Autowired
    private ProductSyncFacade productSyncFacade;



    @GetMapping("/{id}/async")
    public ResponseEntity<ProductDetailDTO> getProductAsync(@PathVariable Long id) {
        log.info("Rest request to get product by id async: {}", id);
        ProductDetailDTO productDetails = productASyncFacade.getProductDetails(id);
        return ResponseEntity.ok(productDetails);
    }

    @GetMapping("/{id}/sync")
    public ResponseEntity<ProductDetailDTO> getProductsync(@PathVariable Long id) {
        log.info("Rest request to get product by id sync: {}", id);
        ProductDetailDTO productDetails = productSyncFacade.getProductDetails(id);
        return ResponseEntity.ok(productDetails);
    }

}
