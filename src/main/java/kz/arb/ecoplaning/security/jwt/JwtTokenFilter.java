package kz.arb.ecoplaning.security.jwt;

import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class JwtTokenFilter extends GenericFilterBean {
    private JwtTokenProvider provider;

    public JwtTokenFilter(JwtTokenProvider provider) {
        this.provider = provider;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String jwtToken = provider.resolveToken((HttpServletRequest) servletRequest);
        System.out.println("wwwwwww " + jwtToken);
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        String path = httpRequest.getRequestURI();
        if (path.equals("/auth/login") || path.equals("/auth/registration")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
    }
}
