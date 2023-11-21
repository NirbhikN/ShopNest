//package com.stackroute.authenticationservice.filter;
//
//import com.stackroute.authenticationservice.security.JwtGeneratorInterface;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//
//import org.springframework.web.filter.GenericFilterBean;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//public class JwtFilter implements Filter {
//
//    private JwtGeneratorInterface jwtGenerator;
//
//    public JwtFilter() {
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        final HttpServletRequest request = (HttpServletRequest) servletRequest;
//        final HttpServletResponse response = (HttpServletResponse) servletResponse;
//        final String authHeader = request.getHeader("authorization");
//        if ("OPTIONS".equals(request.getMethod())) {
//            response.setStatus(HttpServletResponse.SC_OK);
//            filterChain.doFilter(request, response);
//        } else {
//            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//                throw new ServletException("Unauthorized");
//            }
//        }
//        final String token = authHeader.substring(7);
//        {
//            Claims claims = Jwts.parser().setSigningKey("secret").parseClaimsJws(token).getBody();
//            request.setAttribute("claims", claims);
//            filterChain.doFilter(request, response);
//
//        }
//
//    }
//}
