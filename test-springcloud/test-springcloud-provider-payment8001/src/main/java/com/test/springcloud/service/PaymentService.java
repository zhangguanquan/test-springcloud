package com.test.springcloud.service;

import com.test.springcloud.entities.Payment;

public interface PaymentService {
    // 插入
    public int insert(Payment payment);
    // 获取
    public Payment getPaymentById(Long id);
}
