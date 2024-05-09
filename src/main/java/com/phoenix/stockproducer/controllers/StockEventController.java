package com.phoenix.stockproducer.controllers;

import com.phoenix.schema.stock.ProductStock;
import com.phoenix.stockproducer.services.StockProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StockEventController {

    private final StockProducerService stockProducerService;

    @PostMapping("/updateStock")
    public ResponseEntity<String> updateStock(@RequestBody ProductStock productStock) {
        stockProducerService.sendStockUpdate(productStock);
        return ResponseEntity.ok("Stock update sent successfully.");
    }
}
