package com.shinhands.mu.Stationary.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository;
import org.springframework.web.server.session.WebSessionManager;
import reactor.core.publisher.Mono;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Autowired
    private JwtGatewayFilter filter;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http
        .authorizeExchange()
                .pathMatchers(HttpMethod.POST, "/auth/**").permitAll()
                .pathMatchers(HttpMethod.GET, "/currency-converter-feign/**").permitAll()
                .pathMatchers(HttpMethod.GET, "/products/**","/api/bill/**").permitAll()
                .anyExchange()
                .authenticated()
                .and()
                .addFilterAt(filter, SecurityWebFiltersOrder.AUTHENTICATION)
                .securityContextRepository(NoOpServerSecurityContextRepository.getInstance())
                .csrf().disable()
                .httpBasic().disable()
                .formLogin().disable()
                .logout().disable()
        ;

        return http.build();
    }

    @Bean
    public WebSessionManager webSessionManager() {
        // Emulate SessionCreationPolicy.STATELESS
        return exchange -> Mono.empty();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder, LoadBalancerClient loadBalancerClient) {
        return builder.routes()
                .route("auth", r -> r.path("/auth/**").filters(f -> f.filter(jwtAuthenticationFilter)).uri("lb://auth"))
                .route("alert", r -> r.path("/alert/**").filters(f -> f.filter(jwtAuthenticationFilter)).uri("lb://alert"))
                .route("echo", r -> r.path("/echo/**").filters(f -> f.filter(jwtAuthenticationFilter)).uri("lb://echo"))
                .route("products", r -> r.path("/products/**").filters(f -> f.filter(jwtAuthenticationFilter)).uri("lb://PRODUCT-SERVICE/"))
                .route("bill", r -> r.path("/api/bill/**").uri("lb://ORDER-SERVICE/"))
                .route("hello", r -> r.path("/hello/**").filters(f -> f.filter(jwtAuthenticationFilter)).uri("lb://hello"))
                .route("currency-converter", r -> r.path("/currency-converter/**").filters(f -> f.filter(jwtAuthenticationFilter)).uri("lb://CURRENCY-CALCULATION-SERVICE")).build();

    }
}