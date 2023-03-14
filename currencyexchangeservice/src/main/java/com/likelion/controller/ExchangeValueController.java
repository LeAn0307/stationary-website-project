package com.likelion.controller;

import com.likelion.model.ExchangeValue;
import com.likelion.repository.ExchangeValueRepository;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class ExchangeValueController {
    @Autowired
    private ExchangeValueRepository exchangeValueRepository;
    @Autowired
    private Environment environment;
    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public ExchangeValue retrieExchangeValue(@PathVariable String from,@PathVariable String to)
    {
        ExchangeValue exchangeValue=exchangeValueRepository.findByFromAndTo(from,to);
        exchangeValue.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
        return exchangeValue;
    }
}
