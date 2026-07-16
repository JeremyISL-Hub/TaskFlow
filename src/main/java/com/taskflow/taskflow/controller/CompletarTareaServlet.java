package com.taskflow.taskflow.controller;

import com.taskflow.taskflow.dao.TareaDAO;
import com.taskflow.taskflow.model.Usuario;

import java.io.IOException;
import java.util.UUID;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/completarTarea")
public class CompletarTareaServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        Usuario usuario =
                (Usuario) request.getSession().getAttribute("usuario");

        if (usuario == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        UUID id = UUID.fromString(request.getParameter("id"));

        TareaDAO dao = new TareaDAO();

        dao.completarTarea(id, usuario.getId());

        request.getSession().setAttribute(
                "mensaje",
                "✅ ¡Tarea completada!");

        response.sendRedirect("dashboard");
    }

}
