package com.Udemy.Udemy_backend.SecurityConfig;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.List;

public class JwtTokenValidator extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String path = request.getRequestURI();
        if (path.equals("/auth/signin") || path.equals("/auth/signup")) {
            filterChain.doFilter(request, response);
            return;
        }

        String jwt = request.getHeader(JwtConstant.JWT_HEADER);
        System.out.println("JWT Token in JwtTokenValidator: " + jwt);

        if (jwt != null && jwt.startsWith("Bearer ")) {
            jwt = jwt.substring(7); // Remove "Bearer " prefix

            System.out.println("JWT Token in JwtTokenValidator: " + jwt);

            try {
                // Use the secret key to verify the JWT
                SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());

                Claims claims = Jwts.parserBuilder()
                        .setSigningKey(key) // Set the signing key
                        .build() // Build the parser
                        .parseClaimsJws(jwt) // Parse the JWT
                        .getBody(); // Extract the claims

                System.out.println("Claims: " + claims);

                String email = (String) claims.get("email");
                String authorities = (String) claims.get("authorities");

                List<GrantedAuthority> auth = AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);

                Authentication authentication = new UsernamePasswordAuthenticationToken(email, null, auth);
                SecurityContextHolder.getContext().setAuthentication(authentication);

            } catch (Exception e) {
                throw new BadCredentialsException("Invalid token", e);
            }

            // Continue the filter chain
            filterChain.doFilter(request, response);
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        // Exclude endpoints that do not require JWT validation
        return path.equals("/auth/signin") || path.equals("/auth/signup");
    }
}
