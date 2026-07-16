package com.taskflow.taskflow.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConexionBD {

    private static final Properties propiedades = new Properties();

    static {

        try {

            Class.forName("org.postgresql.Driver");

            InputStream input = ConexionBD.class.getClassLoader()
                    .getResourceAsStream("database.properties");

            if (input != null) {
                propiedades.load(input);
                input.close();
            }

        } catch (IOException | ClassNotFoundException e) {

            throw new RuntimeException("Error al cargar la configuración.", e);

        }

    }
    

    public static Connection getConnection() {

        try {

            String url = System.getenv("DB_URL");
            String usuario = System.getenv("DB_USER");
            String password = System.getenv("DB_PASSWORD");

            if (url == null || usuario == null || password == null) {

                url = propiedades.getProperty("db.url");
                usuario = propiedades.getProperty("db.user");
                password = propiedades.getProperty("db.password");

            }

            return DriverManager.getConnection(url, usuario, password);

        } catch (SQLException e) {

            throw new RuntimeException("Error al conectar con la base de datos.", e);

        }

    }

}
