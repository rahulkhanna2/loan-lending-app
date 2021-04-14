package com.peerlender.lendingengine.application;

import com.peerlender.lendingengine.domain.model.Money;
import com.peerlender.lendingengine.domain.service.BalanceService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/balance")
public class BalanceController {
    private final BalanceService balanceService;

    public BalanceController(BalanceService balanceService) {
        this.balanceService = balanceService;
    }

    @PostMapping(value = "/topup")
    public void topUp(@RequestBody final Money money, @RequestHeader String authorization) {
        balanceService.topUpBalance(money, authorization);
    }

    @PostMapping(value = "/withdraw")
    public void withdraw(@RequestBody final Money money, @RequestHeader String authorization) {
        balanceService.withdrawFromBalance(money, authorization);
    }

}
