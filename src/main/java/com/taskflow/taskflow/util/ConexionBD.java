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
        try (InputStream input = ConexionBD.class.getClassLoader()
                .getResourceAsStream("database.properties")) {

            if (input == null) {
                throw new RuntimeException("No se encontró el archivo database.properties");
            }

            propiedades.load(input);

        } catch (IOException e) {
            throw new RuntimeException("Error al cargar el archivo de configuración.", e);
        }
    }

    public static Connection getConnection() {

        try {

            String url = propiedades.getProperty("db.url");
            String usuario = propiedades.getProperty("db.user");
            String password = propiedades.getProperty("db.password");

            return DriverManager.getConnection(url, usuario, password);

        } catch (SQLException e) {

            throw new RuntimeException("Error al conectar con la base de datos.", e);

        }

    }

}
