package com.taskflow.taskflow.config;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import java.util.TimeZone;

@WebListener
public class AppConfig implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        TimeZone.setDefault(TimeZone.getTimeZone("America/Guayaquil"));

        System.out.println("==================================");
        System.out.println("Zona horaria configurada: " + TimeZone.getDefault().getID());
        System.out.println("==================================");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
