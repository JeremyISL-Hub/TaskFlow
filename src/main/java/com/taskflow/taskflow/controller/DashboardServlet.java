package com.taskflow.taskflow.controller;

import com.taskflow.taskflow.dao.TareaDAO;
import com.taskflow.taskflow.model.Tarea;
import com.taskflow.taskflow.model.Usuario;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");

        if (usuario == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        TareaDAO dao = new TareaDAO();

        String buscar = request.getParameter("buscar");
        String estado = request.getParameter("estado");
        String orden = request.getParameter("orden");

        List<Tarea> tareas = dao.buscarFiltrarOrdenar(usuario.getId(), buscar, estado, orden);

        int total = tareas.size();
        int pendientes = 0;
        int completadas = 0;
        int vencidas = 0;

        java.sql.Timestamp ahora = new java.sql.Timestamp(System.currentTimeMillis());

        for (Tarea tarea : tareas) {

            if ("Completada".equalsIgnoreCase(tarea.getEstado())) {
                completadas++;
            } else {

                if (tarea.getFechaLimite() != null &&
                        tarea.getFechaLimite().before(ahora)) {

                    vencidas++;

                } else {

                    pendientes++;

                }
            }
        }

        int porcentaje = total == 0 ? 0 : (completadas * 100) / total;

        request.setAttribute("tareas", tareas);
        request.setAttribute("total", total);
        request.setAttribute("pendientes", pendientes);
        request.setAttribute("completadas", completadas);
        request.setAttribute("vencidas", vencidas);
        request.setAttribute("porcentaje", porcentaje);

        request.getRequestDispatcher("dashboard.jsp").forward(request, response);
    }

}