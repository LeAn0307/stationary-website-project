//package com.shinhands.mu.Stationary.filter;
//
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//import org.springframework.web.server.WebFilter;
//import org.springframework.web.server.WebFilterChain;
//import reactor.core.publisher.Mono;
//
//@Component
//public class AddControlHeaderWebFilter implements WebFilter {
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
//        exchange.getResponse()
//                .getHeaders()
//                .add("Access-Control-Allow-Headers", "*");
//        return chain.filter(exchange);
//    }
//}
