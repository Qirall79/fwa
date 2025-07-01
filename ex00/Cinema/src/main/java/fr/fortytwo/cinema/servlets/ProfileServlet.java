package fr.fortytwo.cinema.servlets;

import java.io.IOException;

import org.springframework.context.ApplicationContext;

import fr.fortytwo.cinema.models.User;
import fr.fortytwo.cinema.services.UsersService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

    private UsersService usersService;

    public ProfileServlet() {}

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        ApplicationContext springContext = (ApplicationContext) context.getAttribute("springContext");
        
        this.usersService = springContext.getBean(UsersService.class);
    }

    

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        User user = (User) req.getSession().getAttribute("user");

        if (user == null) {
            resp.sendRedirect("/cinema/signIn");
            return ;
        }

        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().println("<h1>Hello, %s!</h1>".formatted(user.getFirstName()));
    }
}
