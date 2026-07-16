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

@WebServlet("/eliminarTarea")
public class EliminarTareaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");

        if (usuario == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String id = request.getParameter("id");

        if (id != null && !id.isEmpty()) {
            TareaDAO dao = new TareaDAO();
            dao.eliminarTarea(UUID.fromString(id), usuario.getId());
        }

        request.getSession().setAttribute("mensaje", "🗑️ Tarea eliminada correctamente.");
        response.sendRedirect("dashboard");
    }

}