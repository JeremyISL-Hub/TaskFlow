package com.taskflow.taskflow.controller;

import com.taskflow.taskflow.dao.TareaDAO;
import com.taskflow.taskflow.model.Tarea;
import com.taskflow.taskflow.model.Usuario;

import java.io.IOException;
import java.util.UUID;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/editarTarea")
public class EditarTareaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");

        if (usuario == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String id = request.getParameter("id");

        if (id == null || id.isEmpty()) {
            response.sendRedirect("dashboard");
            return;
        }

        TareaDAO dao = new TareaDAO();
        Tarea tarea = dao.buscarPorIdYUsuario(UUID.fromString(id), usuario.getId());

        if (tarea == null) {
            response.sendRedirect("dashboard");
            return;
        }

        request.setAttribute("tarea", tarea);
        request.getRequestDispatcher("editarTarea.jsp").forward(request, response);
    }

}