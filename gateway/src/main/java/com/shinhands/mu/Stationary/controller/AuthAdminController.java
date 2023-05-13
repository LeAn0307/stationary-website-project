package com.shinhands.mu.Stationary.controller;

import com.shinhands.mu.Stationary.dto.AccountDTO;
import com.shinhands.mu.Stationary.dto.AuthDTO;
import com.shinhands.mu.Stationary.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.client.RestTemplate;

import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;



import java.time.Duration;

@Controller
@RequestMapping("/admin")
public class AuthAdminController {


    @Autowired
    private WebClient.Builder webClientBuilder;

    @Autowired
    private JwtUtil jwtUtil;

    private String urlPage = "http://localhost:8099";

    @PostMapping(value = "/login")
    public Mono<String> loginAdmin(@RequestBody AuthDTO authDTO, Model model, ServerHttpResponse response) {

        WebClient client = webClientBuilder.baseUrl(urlPage).build();

        Mono<Boolean> isAuthenMono = client.post()
                .uri("/api/accounts/authen")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(authDTO))
                .retrieve()
                .bodyToMono(Boolean.class);

        return isAuthenMono.flatMap(isAuthen -> {
            if (isAuthen) {
                Mono<AccountDTO> accountMono = client.get()
                        .uri("/api/accounts/" + authDTO.getEmail())
                        .accept(MediaType.APPLICATION_JSON)
                        .retrieve()
                        .bodyToMono(AccountDTO.class);

                return accountMono.flatMap(accountDTO -> {
                    String token = jwtUtil.generateToken(accountDTO);
                    ResponseCookie cookie = ResponseCookie.from("JWT", token)
                            .maxAge(Duration.ofHours(1))
                            .httpOnly(true)
                            .path("/")
                            .build();
                    response.addCookie(cookie);
                    return Mono.just("redirect:/admin/dashboard");
                });
            } else {
                model.addAttribute("error", "Đăng nhập thất bại");
                return Mono.just("login");
            }
        });
    }
}
