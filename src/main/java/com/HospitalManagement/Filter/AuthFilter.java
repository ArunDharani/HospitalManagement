// Filter Created by Arun on Tuesday(10/02/26)

// Importing the necessary packages
package com.HospitalManagement.Filter;
import com.HospitalManagement.Services.Service_Jwt;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

// Creation of 'AuthFilter' class
@Component
public class AuthFilter extends OncePerRequestFilter {

    // Creation of instance of Service_Jwt
    private final Service_Jwt serviceJwt;

    // Creation of constructor
    public AuthFilter(Service_Jwt serviceJwt) {
        this.serviceJwt = serviceJwt;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

//            // Let us obtain the path
//            String path = request.getServletPath();
//            if (path.startsWith("/auth")) {
//                filterChain.doFilter(request ,response);
//                return;
//            }

            // Obtaining the token
            String entireToken = request.getHeader("Authorization");
            // Verifying the token
            if (entireToken != null && entireToken.startsWith("Bearer ")) {
                // Extracting the data from token
                String email = serviceJwt.extractUserEmail(entireToken.substring(7));
                String name = serviceJwt.extractUserName(entireToken.substring(7));
                if (email != null && name != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    // Creation of Token using 'UserNamePasswordAuthenticationToken' class
                    UsernamePasswordAuthenticationToken newAuthToken = new UsernamePasswordAuthenticationToken(email , null, List.of());
                    SecurityContextHolder.getContext().setAuthentication(newAuthToken);
                }
            }
            // let us process the http
        filterChain.doFilter(request , response);
    }
}
