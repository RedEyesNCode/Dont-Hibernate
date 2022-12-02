package com.leadexperience.leadexperince.security;


import com.google.gson.Gson;
import com.leadexperience.leadexperince.models.StatusCodeModel;
import com.leadexperience.leadexperince.utils.JWTUtil;
import io.jsonwebtoken.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    @Autowired
    MyUserDetails userDetailsService;

    @Autowired
    JWTUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException, SignatureException {
        final String authHeader = request.getHeader("Authorization");
        String username = null;
        String jwt = "";

        if(authHeader != null) {

            jwt = authHeader;

            try{
                username = jwtUtil.extractUserName(jwt);
            }catch (SignatureException e){
                response.addHeader("Content-Type","application/json");
                response.setContentType("application/json");
                Gson gson = new Gson();
                String json = gson.toJson(new StatusCodeModel("fail",403,"Invalid Token"));
                response.getWriter().print(json);
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                //				e.printStackTrace();
                return;
            }
            //HANDLING JWT SIGNATURE EXCEPTION USING THE CLASS WHICH IS GOOD


        }

        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null ) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            if(jwtUtil.validateToken(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken userPassAuthToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                userPassAuthToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(userPassAuthToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
