package com.taskflow.taskflow.util;

import java.sql.Connection;

public class ConexionTest {

    public static void main(String[] args) {

        try {

            Connection conexion = ConexionBD.getConnection();

            if (conexion != null) {

                System.out.println("=================================");
                System.out.println("Conexión exitosa a Supabase");
                System.out.println("=================================");

                conexion.close();

            }

        } catch (Exception e) {

            System.out.println("Error de conexión:");

            e.printStackTrace();

        }

    }

}
