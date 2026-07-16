package com.taskflow.taskflow.controller;

import com.taskflow.taskflow.dao.UsuarioDAO;
import com.taskflow.taskflow.model.Usuario;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/registro")
public class RegistroServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nombre = request.getParameter("nombre");
        String correo = request.getParameter("correo");
        String password = request.getParameter("password");

        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setCorreo(correo);
        usuario.setPassword(password);

        UsuarioDAO dao = new UsuarioDAO();
        boolean registrado = dao.registrarUsuario(usuario);

        if (registrado) {
            request.getSession().setAttribute(
                    "mensajeLogin",
                    "🎉 Usuario registrado correctamente. Ahora puedes iniciar sesión."
            );
            response.sendRedirect("login.jsp");
        } else {
            request.setAttribute("error", "No fue posible registrar el usuario.");
            request.getRequestDispatcher("registro.jsp").forward(request, response);
        }
    }

}