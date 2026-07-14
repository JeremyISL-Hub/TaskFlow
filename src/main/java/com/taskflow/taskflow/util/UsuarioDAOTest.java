package com.taskflow.taskflow.util;

import com.taskflow.taskflow.dao.UsuarioDAO;
import com.taskflow.taskflow.model.Usuario;

public class UsuarioDAOTest {

    public static void main(String[] args) {

        Usuario usuario = new Usuario(
                "Jeremy",
                "Jeremy@test.com",
                "789123"
        );

        UsuarioDAO dao = new UsuarioDAO();

        boolean registrado = dao.registrarUsuario(usuario);

        if (registrado) {
            System.out.println("=================================");
            System.out.println("Usuario registrado correctamente.");
            System.out.println("=================================");
        } else {
            System.out.println("No se pudo registrar el usuario.");
        }

    }

}