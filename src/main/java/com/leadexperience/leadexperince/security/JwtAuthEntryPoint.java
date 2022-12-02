package com.leadexperience.leadexperince.security;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.leadexperience.leadexperince.models.StatusCodeModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.security.core.AuthenticationException;
import java.io.IOException;

@Component
public class JwtAuthEntryPoint   implements AuthenticationEntryPoint {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthEntryPoint.class);


    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                         AuthenticationException e) throws IOException, ServletException {
        logger.error("Responding with unauthorized error. Message - {}", e.getMessage());



        ServletOutputStream out = httpServletResponse.getOutputStream();
        ServletOutputStream outputStream = httpServletResponse.getOutputStream();
        new ObjectMapper().writeValue(out, new StatusCodeModel("fail",401,"Invalid Token"));
        out.flush();
    }

}
