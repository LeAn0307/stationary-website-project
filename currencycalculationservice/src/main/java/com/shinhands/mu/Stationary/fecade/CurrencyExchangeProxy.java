package com.shinhands.mu.Stationary.fecade;

import com.shinhands.mu.Stationary.model.CalculatedAmount;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name="CURRENCY-EXCHANGE-SERVICE")
@FeignClient(name ="CURRENCY-EXCHANGE-SERVICE")
public interface CurrencyExchangeProxy {
    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    CalculatedAmount retrieExchangeValue(@PathVariable String from, @PathVariable String to);
}
