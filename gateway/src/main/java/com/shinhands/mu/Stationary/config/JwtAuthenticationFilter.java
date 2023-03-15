package com.shinhands.mu.Stationary.config;

import com.shinhands.mu.Stationary.exception.JwtTokenMalformedException;
import com.shinhands.mu.Stationary.exception.JwtTokenMissingException;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.function.Predicate;

@Component
public class JwtAuthenticationFilter implements GatewayFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = (ServerHttpRequest) exchange.getRequest();

        final List<String> apiEndpoints = List.of("/register", "/login");

        Predicate<ServerHttpRequest> isApiSecured = r -> apiEndpoints.stream()
                .noneMatch(uri -> r.getURI().getPath().contains(uri));

        if (isApiSecured.test(request)) {
            if (!request.getHeaders().containsKey("Authorization")) {
                ServerHttpResponse response = exchange.getResponse();
                response.setStatusCode(HttpStatus.UNAUTHORIZED);

                return response.setComplete();
            }

            final String token = request.getHeaders().getOrEmpty("Authorization").get(0).substring(7);


            try {
                jwtUtil.validateToken(token);
            } catch (JwtTokenMalformedException | JwtTokenMissingException e) {
                // e.printStackTrace();

                ServerHttpResponse response = exchange.getResponse();
                response.setStatusCode(HttpStatus.BAD_REQUEST);

                return response.setComplete();
            } catch (Exception e) {
                e.printStackTrace();
            }

            Claims claims = jwtUtil.getClaims(token);
            exchange.getRequest().mutate().header("id", String.valueOf(claims.get("id"))).build();
        }

        return chain.filter(exchange);
    }

}

//    @Autowired
//    private ReactiveUserDetailsService userDetailsService;
//
//    @Autowired
//    private ServerAccessDeniedHandler accessDeniedHandler;
//
//    @Autowired
//    private ServerAuthenticationEntryPoint authenticationEntryPoint;
//
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
//        String authorizationHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
//        if (StringUtils.isNotBlank(authorizationHeader) && authorizationHeader.startsWith("Bearer ")) {
//            String token = authorizationHeader.substring(7);
//            try {
//                Claims claims = Jwts.parser().setSigningKey("secret".getBytes()).parseClaimsJws(token).getBody();
//                String username = claims.getSubject();
//                List<String> authorities = (List<String>) claims.get("authorities");
//
//                Mono<UserDetails> userDetailsMono = userDetailsService.findByUsername(username);
//                return userDetailsMono.flatMap(userDetails -> {
//                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, AuthorityUtils.createAuthorityList(authorities.toArray(new String[0])));
//                    return ReactiveSecurityContextHolder.getContext()
//                            .map(context -> {
//                                context.setAuthentication(authenticationToken);
//                                return context;
//                            })
//                            .flatMap(context -> accessDeniedHandler.handle(exchange, new AccessDeniedException("Access Denied")))
//                            .switchIfEmpty(chain.filter(exchange))
//                            .onErrorResume(AuthenticationException.class, e -> authenticationEntryPoint.commence(exchange, e));
//                });
//            } catch (JwtException e) {
//                return accessDeniedHandler.handle(exchange, new AccessDeniedException("Invalid Token"));
//            }
//        } else {
//            return accessDeniedHandler.handle(exchange, new AccessDeniedException("Authorization header not found"));
//        }
//    }