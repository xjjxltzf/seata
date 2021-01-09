package com.tao.cloud2020.service;

public interface StorageService {

    // 扣减库存
    void decrease(Long productId, Integer count);
}
