package com.spring.beebeta.service;

import com.spring.beebeta.repository.ProductDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class CheckDiscountExpiredService {
    @Autowired
    ProductDetailRepository repository;
    @Scheduled(cron = "0 0 0 * * *")
    public void updateExpiredDiscounts() {
        System.out.println("Đã check giảm giá hết hạn");
        repository.updateDiscount();
    }
}
