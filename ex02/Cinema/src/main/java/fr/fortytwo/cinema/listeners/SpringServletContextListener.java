package fr.fortytwo.cinema.listeners;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.PropertySource;

import fr.fortytwo.cinema.config.ApplicationConfig;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class SpringServletContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ApplicationContext context = (ApplicationContext) new AnnotationConfigApplicationContext(ApplicationConfig.class);

        sce.getServletContext().setAttribute("springContext", context);

        ServletContextListener.super.contextInitialized(sce);

    }
}
