package com.example.springsecurityjwt.filter;

import com.example.springsecurityjwt.service.CustomUserDetailService;
import com.example.springsecurityjwt.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomUserDetailService service;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String autherizationHead = request.getHeader("Authorization");
        String tokens =null;
        String username= null;
        if(autherizationHead != null && autherizationHead.startsWith("Bearer ")){
            tokens = autherizationHead.substring(7);
            username= jwtUtil.extractUsername(tokens);
        }
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null){
               UserDetails userDetails= service.loadUserByUsername(username);
                if (jwtUtil.validateToken(tokens, userDetails )) {
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }

        }
        filterChain.doFilter(request,response);
    }
}
