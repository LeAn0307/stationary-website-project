package com.shinhands.mu.Stationary.security;

import com.shinhands.mu.Stationary.config.WebConfig;
import io.netty.handler.codec.http.cors.CorsConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.session.WebSessionManager;
import reactor.core.publisher.Mono;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Autowired
    private JwtGatewayFilter filter;

//    @Autowired
//    private JwtAuthenticationFilter jwtAuthenticationFilter;
//    @Autowired
//    private CorsWebFilter corsWebFilter;

    @Autowired
    private WebConfig webConfig;

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http
        .authorizeExchange()
                .pathMatchers("/admin/login/**","/auth/**").permitAll()
                .pathMatchers("/admin/**").hasRole("ADMIN")
                .pathMatchers("/api/users/**","/api/carts/**","/api/bills/**","/api/cartproducts/**").hasAnyAuthority("USER","ADMIN")
                .pathMatchers("/api/hello").hasAuthority("USER")
                .pathMatchers("/api/**","/images/**").permitAll()
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
                .route("products", r -> r.path("/api/ratings","/api/products/**","/admin/category/**","/admin/product/**","/admin/product_detail/**").uri("lb://PRODUCT-SERVICE/"))
                .route("bill", r -> r.path("/api/bills/**","/admin/bill/**").uri("lb://ORDER-SERVICE/"))
                .route("cart", r -> r.path("/api/carts/**","/api/cartcoupon/**","/api/cartproducts/**","/api/coupons/**","/admin/coupon/**").uri("lb://CART-SERVICE/"))
                .route("user", r -> r.path("/api/accounts/**","/api/users/**","/admin/user/**","/admin/delete-user/**").uri("lb://USER-SERVICE/"))
                .build();
    }
}