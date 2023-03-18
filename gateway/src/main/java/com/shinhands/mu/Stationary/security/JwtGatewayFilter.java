package com.shinhands.mu.Stationary.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.Disposable;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class JwtGatewayFilter implements WebFilter {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private ReactiveUserDetailsService userDetailsService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String authorizationHeader = exchange.getRequest().getHeaders().getFirst(AUTHORIZATION_HEADER);
        if (authorizationHeader != null && authorizationHeader.startsWith(BEARER_PREFIX)) {
            String token = authorizationHeader.substring(BEARER_PREFIX.length());
            try {
                Jws<Claims> claims = Jwts.parser().setSigningKey(jwtUtil.getSecretKey()).parseClaimsJws(token);
                String username = claims.getBody().getSubject();
                userDetailsService.findByUsername(username).subscribe(userDetails1 -> {
                    Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails1, null, userDetails1.getAuthorities());
                    exchange.getAttributes().put("AUTHENTICATION_ATTRIBUTE_KEY", authentication);
                    chain.filter(exchange)
                            .contextWrite(ReactiveSecurityContextHolder.withAuthentication(authentication))
                            .subscribe();
                }, error -> {
                    log.error(error.toString());
                    exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                    exchange.getResponse().setComplete().subscribe();
                });

            } catch (Exception e) {
                log.error(e.toString());
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }
        }
        return chain.filter(exchange);
    }
}