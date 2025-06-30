package fr.fortytwo.cinema.listeners;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import fr.fortytwo.cinema.config.ApplicationConfig;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class SpringServletContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            Class.forName("org.postgresql.Driver");

        } catch (ClassNotFoundException e) {
        }

        ApplicationContext context = (ApplicationContext) new AnnotationConfigApplicationContext(ApplicationConfig.class);

        sce.getServletContext().setAttribute("springContext", context);

        ServletContextListener.super.contextInitialized(sce);

    }
}
