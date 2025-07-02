package fr.fortytwo.cinema.filters;

import java.io.IOException;

import fr.fortytwo.cinema.models.User;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        
        User user = (User) req.getSession().getAttribute("user");
        chain.doFilter(request, response);

        if (user == null) {
            res.sendError(403);
            return ;
        }
        chain.doFilter(request, response);
    }

}
