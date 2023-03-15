package com.shinhands.mu.Stationary.controller;

import com.shinhands.mu.Stationary.fecade.CurrencyExchangeProxy;
import com.shinhands.mu.Stationary.model.CalculatedAmount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CurrencyCalculationController {
    private Logger logger= LoggerFactory.getLogger(CurrencyCalculationController.class);
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private CurrencyExchangeProxy proxy;
    @GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
    public CalculatedAmount calculatedAmount(@PathVariable(name = "from") String from, @PathVariable(name = "to") String to , @PathVariable(name="quantity") BigDecimal quantity)
    {
        Map<String, String> uriVariables=new HashMap<>();
        uriVariables.put("from",from);
        uriVariables.put("to",to);
        ResponseEntity<CalculatedAmount> responseEntity=restTemplate.getForEntity("http://CURRENCY-EXCHANGE-SERVICE/currency-exchange/from/{from}/to/{to}",CalculatedAmount.class,uriVariables);

        CalculatedAmount calculatedAmount=responseEntity.getBody();
        return new CalculatedAmount(calculatedAmount.getId(),calculatedAmount.getFrom(),calculatedAmount.getTo(),calculatedAmount.getConversionMultiple(),quantity,quantity.multiply(calculatedAmount.getConversionMultiple()),calculatedAmount.getPort());
    }
    @GetMapping("/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CalculatedAmount calculatedAmountFeign(@PathVariable String from, @PathVariable String to,@PathVariable BigDecimal quantity )
    {
        CalculatedAmount calculatedAmount=proxy.retrieExchangeValue(from,to);
        logger.info("Port: "+ calculatedAmount.getPort());
        return new CalculatedAmount(calculatedAmount.getId(),calculatedAmount.getFrom(),calculatedAmount.getTo(),calculatedAmount.getConversionMultiple(),quantity,quantity.multiply(calculatedAmount.getConversionMultiple()),calculatedAmount.getPort());
    }


}
