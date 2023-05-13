package com.shinhands.mu.Stationary.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.server.session.WebSessionManager;
import reactor.core.publisher.Mono;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Autowired
    private JwtGatewayFilter filter;

    @Bean
    public WebFluxConfigurer corsConfigurer() {
        return new WebFluxConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedHeaders("*")
                        .allowedMethods("*")
                        .maxAge(6000);
            }
        };
    }

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http
                .headers()
                .contentTypeOptions().disable().and()
                .authorizeExchange()

                .pathMatchers("/images/**","/js/**","/css/**","/vendors/**").permitAll()
                .pathMatchers("/admin/login/**","/auth/**").permitAll()
                .pathMatchers("/admin/**").hasAuthority("ADMIN")
                .pathMatchers("/api/users/**","/api/carts/**","/api/bills/**","/api/cartproducts/**").hasAnyAuthority("USER","ADMIN")
                .pathMatchers("/api/hello").hasAuthority("USER")
                .pathMatchers("/api/**").permitAll()
                .anyExchange()
                .authenticated()
                .and()
                .addFilterAt(filter, SecurityWebFiltersOrder.AUTHENTICATION)
                .securityContextRepository(NoOpServerSecurityContextRepository.getInstance())
                .csrf().disable()
                .cors()
                .and()
                .httpBasic().and()
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
                .route("products", r -> r.path("/api/ratings","/api/products/**","/admin/category/**","/admin/product/**","/admin/product_detail/**", "/js/**", "/css/**", "/vendors/**", "/images/**", "/favicon.ico")
                        .uri("lb://PRODUCT-SERVICE/"))
                .route("bill", r -> r.path("/api/bills/**","/admin/bill/**","/api/historyOrder/**")
                        .uri("lb://ORDER-SERVICE/"))
                .route("cart", r -> r.path("/api/carts/**","/api/cartcoupon/**","/api/cartproducts/**","/api/coupons/**","/admin/coupon/**")
                        .uri("lb://CART-SERVICE/"))
                .route("user", r -> r.path("/api/accounts/**","/api/users/**","/admin/user/**","/admin/delete-user/**")
                        .uri("lb://USER-SERVICE/"))
                .build();
    }
}