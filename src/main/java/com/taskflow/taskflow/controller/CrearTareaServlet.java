package com.taskflow.taskflow.controller;

import com.taskflow.taskflow.dao.TareaDAO;
import com.taskflow.taskflow.model.Tarea;
import com.taskflow.taskflow.model.Usuario;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/crearTarea")
public class CrearTareaServlet extends HttpServlet {

    @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");

        if (usuario == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String titulo = request.getParameter("titulo");
        String descripcion = request.getParameter("descripcion");
        String fechaHora = request.getParameter("fechaLimite");
        
        Tarea tarea = new Tarea();

        tarea.setTitulo(titulo);
        tarea.setDescripcion(descripcion);

        // Todas las tareas nuevas empiezan pendientes
        tarea.setEstado("Pendiente");

        

        if (fechaHora != null && !fechaHora.isEmpty()) {

            LocalDateTime fecha = LocalDateTime.parse(fechaHora);

            tarea.setFechaLimite(Timestamp.valueOf(fecha));

        }

        tarea.setUsuarioId(usuario.getId());

        TareaDAO dao = new TareaDAO();

        if (dao.crearTarea(tarea)) {
            request.getSession().setAttribute("mensaje",
                    "✅ Tarea creada correctamente.");
            response.sendRedirect("dashboard");
        } else {
            request.setAttribute("error",
                    "No fue posible guardar la tarea.");
            request.getRequestDispatcher("nuevaTarea.jsp")
                    .forward(request, response);
        }
    }
}