package com.phoenix.stockproducer.services;

import com.phoenix.stockproducer.schema.ProductStock;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class StockProducerService {

    private final KafkaTemplate<String, ProductStock> kafkaTemplate;

    @Value("${spring.kafka.topic}")
    private String stockTopic;

    public void sendStockUpdate(ProductStock productStock) {
        kafkaTemplate.send(stockTopic, productStock);
    }

    @Scheduled(cron = "0 0 15 * * *")  // every day at 3pm
    public void generateRandomStockUpdate() {
        for (int sku = 1; sku <= 10; sku++) {
            var quantity = ThreadLocalRandom.current().nextLong(1, 100);
            kafkaTemplate.send(stockTopic, new ProductStock(sku, quantity, "default"));
        }
    }
}
