package com.tao.cloud2020.service.impl;

import com.tao.cloud2020.dao.AccountDao;
import com.tao.cloud2020.service.AccountService;
import io.seata.core.context.RootContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 账户业务实现类
 */
@Service
public class AccountServiceImpl implements AccountService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);


    @Resource
    AccountDao accountDao;

    /**
     * 扣减账户余额
     */
    //Lock lock = new ReentrantLock();

    @Override
    public void decrease(Long userId, BigDecimal money) {

        LOGGER.info("------->account-service中扣减账户余额开始");
        //try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
        System.out.println(1/0);
       /* try {
            lock.lock();*/
        LOGGER.info("当前XID===>" + RootContext.getXID());
        accountDao.decrease(userId, money);
        LOGGER.info("------->account-service中扣减账户余额结束");
        /*} finally {
            // TODO: handle finally clause
            lock.unlock();
        }*/
    }


}
