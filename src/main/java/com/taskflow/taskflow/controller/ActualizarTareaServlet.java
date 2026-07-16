package com.taskflow.taskflow.controller;

import com.taskflow.taskflow.dao.TareaDAO;
import com.taskflow.taskflow.model.Tarea;
import com.taskflow.taskflow.model.Usuario;

import java.io.IOException;
import java.sql.Date;
import java.util.UUID;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/actualizarTarea")
public class ActualizarTareaServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");

        if (usuario == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        Tarea tarea = new Tarea();
        tarea.setId(UUID.fromString(request.getParameter("id")));
        tarea.setTitulo(request.getParameter("titulo"));
        tarea.setDescripcion(request.getParameter("descripcion"));
        tarea.setEstado(request.getParameter("estado"));

        String fecha = request.getParameter("fechaLimite");

        if (fecha != null && !fecha.isEmpty()) {
            tarea.setFechaLimite(Date.valueOf(fecha));
        }

        TareaDAO dao = new TareaDAO();
        Tarea existente = dao.buscarPorIdYUsuario(tarea.getId(), usuario.getId());

        if (existente != null) {
            dao.actualizarTarea(tarea);
        }

        request.getSession().setAttribute("mensaje", "✏️ Tarea actualizada correctamente.");
        response.sendRedirect("dashboard");
    }

}