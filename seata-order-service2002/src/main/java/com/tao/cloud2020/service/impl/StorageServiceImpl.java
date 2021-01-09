package com.tao.cloud2020.service.impl;

import com.tao.cloud2020.dao.StorageDao;
import com.tao.cloud2020.service.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


@Service
public class StorageServiceImpl implements StorageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StorageServiceImpl.class);

    @Resource
    private StorageDao storageDao;
    //Lock lock = new ReentrantLock();

    // 扣减库存
    @Override
    public void decrease(Long productId, Integer count) {
        /*try {
            lock.lock();*/
            LOGGER.info("------->storage-service中扣减库存开始");
            storageDao.decrease(productId, count);
            LOGGER.info("------->storage-service中扣减库存结束");
        /*} finally {
            // TODO: handle finally clause
            lock.unlock();
        }*/
    }
}
