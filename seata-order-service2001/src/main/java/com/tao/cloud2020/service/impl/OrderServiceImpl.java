package com.tao.cloud2020.service.impl;


import com.tao.cloud2020.dao.OrderDao;
import com.tao.cloud2020.domai.Order;
import com.tao.cloud2020.service.AccountService;
import com.tao.cloud2020.service.OrderService;
import com.tao.cloud2020.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderDao orderDao;
    @Resource
    private StorageService storageService;
    @Resource
    private AccountService accountService;

    /**
     * 创建订单->调用库存服务扣减库存->调用账户服务扣减账户余额->修改订单状态
     */
    Lock lock = new ReentrantLock();

    @Override
    @GlobalTransactional(name = "fsp-create-order", rollbackFor = Exception.class)//可以指定异常类型回滚
    public void create(Order order) {

        try {
            lock.lock();
            log.info("----->开始新建订单");
            //新建订单
            orderDao.create(order);

            //扣减库存
            log.info("----->订单微服务开始调用库存，做扣减Count");
            storageService.decrease(order.getProductId(), order.getCount());
            log.info("----->订单微服务开始调用库存，做扣减end");

            //扣减账户
            log.info("----->订单微服务开始调用账户，做扣减Money");
            accountService.decrease(order.getUserId(), order.getMoney());
            log.info("----->订单微服务开始调用账户，做扣减end");
           /* try {
                //异常不能捕获，捕获后事务失效
                System.out.println(1/0);
            } catch (Exception e) {
                e.printStackTrace();
            }*/

            //修改订单状态，从零到1代表已经完成
            log.info("----->修改订单状态开始");
            orderDao.update(order.getUserId(), 0);
            log.info("----->修改订单状态结束");

            log.info("----->下订单结束了");
        } finally {
            // TODO: handle finally clause
            lock.unlock();
        }


    }
}
