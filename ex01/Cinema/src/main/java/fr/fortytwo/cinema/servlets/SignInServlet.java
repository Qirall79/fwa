package fr.fortytwo.cinema.servlets;

import java.io.IOException;

import org.springframework.context.ApplicationContext;

import fr.fortytwo.cinema.models.User;
import fr.fortytwo.cinema.services.UsersService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/signIn")
public class SignInServlet extends HttpServlet {

    private UsersService usersService;

    public SignInServlet() {
    }

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        ApplicationContext context = (ApplicationContext) servletConfig.getServletContext().getAttribute("springContext");
        usersService = context.getBean(UsersService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("user") != null) {
            resp.sendRedirect("/cinema/profile");
            return;
        }

        resp.setContentType("text/html;charset=UTF-8");

        String html = "<h1>Sign In</h1>"
                + "<form action='/cinema/signIn' method='POST'>"
                + "<input type='text' placeholder='phone number' name='phone' />"
                + "<input type='password' placeholder='password' name='password' />"
                + "<button type='submit'>Submit</button>"
                + "</form>";

        resp.getWriter().println(html);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String phoneNumber = req.getParameter("phone");
        String password = req.getParameter("password");

        User user = usersService.signInUser(phoneNumber, password);

        req.getSession().setAttribute("user", user);
    }
}
