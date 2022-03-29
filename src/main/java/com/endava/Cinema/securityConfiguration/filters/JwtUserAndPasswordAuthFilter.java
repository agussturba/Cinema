package com.endava.Cinema.securityConfiguration.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

public class JwtUserAndPasswordAuthFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager aunthenticationManager;

    public JwtUserAndPasswordAuthFilter(AuthenticationManager authenticationManager) {
        this.aunthenticationManager = authenticationManager;
    }

    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        UserAndPassVO userAndPassVO;
        try {
            userAndPassVO = new ObjectMapper().readValue(request.getInputStream(), UserAndPassVO.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Authentication authentication = new UsernamePasswordAuthenticationToken(userAndPassVO.username, userAndPassVO.password);
        return aunthenticationManager.authenticate(authentication);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String key = "should_be_a_very_big_keyyy_should_be_a_very_big_keyyy_should_be_a_very_big_keyyy";
        String token = Jwts.builder()
                .setSubject(authResult.getName())
                .claim("authorities",authResult.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusWeeks(2)))
                .signWith(Keys.hmacShaKeyFor(key.getBytes()))
                .compact();
    response.addHeader("Authorization","Bearer "+token);
    }
}

