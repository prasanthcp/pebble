package com.prasanthprojects.pebble.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
//import lombok.AllArgsConstructor;
//import lombok.NoArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

@Component
//@AllArgsConstructor @NoArgsConstructor
public class jwtfilter extends OncePerRequestFilter {
    public jwtfilter() {
    }

    public jwtfilter(jwtutil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    public jwtutil getJwtUtil() {
        return jwtUtil;
    }

    public void setJwtUtil(jwtutil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    private jwtutil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        if ("/auth/login".equals(request.getServletPath())) {
            filterChain.doFilter(request, response); return;
        }

        String header = request.getHeader("Authorization");
        if(header!=null && header.startsWith("Bearer")) {
            String token = header.substring(7);
            String username = jwtUtil.getUsername(token);
            if( jwtUtil.validateToken(token,username) ) {
                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(username,null,new ArrayList<>());
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        filterChain.doFilter(request, response);
    }
}
