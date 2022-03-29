package com.endava.Cinema.securityConfiguration.filters;

import com.google.common.base.Strings;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class JwtTokenVerifier extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader=request.getHeader("Authorization");
        if(Strings.isNullOrEmpty(authHeader) || !authHeader.startsWith("Bearer")){
            filterChain.doFilter(request,response);
            return;
        }
        String token = authHeader.replace("Bearer ","");
        String key = "should_be_a_very_big_keyyy_should_be_a_very_big_keyyy_should_be_a_very_big_keyyy";
        try {
            Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(key.getBytes())).build().parseClaimsJws(token);
            Claims body = claimsJws.getBody();
            String username = body.getSubject();
            List<Map<String, String>> authorities = new ArrayList<>();
            authorities = body.get("authorities", authorities.getClass());
            Set<GrantedAuthority> grantedAuthorities = authorities.stream().map(stringStringMap -> new SimpleGrantedAuthority(stringStringMap.get("authority"))).collect(Collectors.toSet());
            Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, grantedAuthorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }catch (JwtException e){
            throw new IllegalStateException("Invalid Token!");
        }
        filterChain.doFilter(request,response);
    }
}
