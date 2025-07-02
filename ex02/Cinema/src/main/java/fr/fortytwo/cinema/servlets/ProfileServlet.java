package fr.fortytwo.cinema.servlets;

import java.io.IOException;
import java.util.Set;

import org.springframework.context.ApplicationContext;

import fr.fortytwo.cinema.models.User;
import fr.fortytwo.cinema.repositories.AuthenticationsRepository;
import fr.fortytwo.cinema.repositories.AvatarsRepository;
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
    private AuthenticationsRepository authenticationsRepository;
    private AvatarsRepository avatarsRepository;
    

    public ProfileServlet() {
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        ApplicationContext springContext = (ApplicationContext) context.getAttribute("springContext");

        usersService = springContext.getBean(UsersService.class);
        authenticationsRepository = springContext.getBean(AuthenticationsRepository.class);
        avatarsRepository = springContext.getBean(AvatarsRepository.class);

    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        User user = (User) req.getSession().getAttribute("user");
        req.setAttribute("authentications", authenticationsRepository.findByUserId(user.getId()));
        req.setAttribute("avatars", avatarsRepository.findByUserId(user.getId()));
        
        req.getRequestDispatcher("/WEB-INF/jsp/profile.jsp").forward(req, resp);
    }
}
