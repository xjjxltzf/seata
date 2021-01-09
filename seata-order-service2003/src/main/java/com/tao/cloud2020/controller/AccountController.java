package com.tao.cloud2020.controller;

import com.tao.cloud2020.domain.CommonResult;
import com.tao.cloud2020.service.AccountService;
import io.seata.core.context.RootContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;

@RestController
public class AccountController {

    @Resource
    AccountService accountService;

    /**
     * 扣减账户余额
     */
    @RequestMapping("/account/decrease")
    public CommonResult decrease(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money){

        accountService.decrease(userId,money);
        return new CommonResult(200,"扣减账户余额成功！");
    }
}
