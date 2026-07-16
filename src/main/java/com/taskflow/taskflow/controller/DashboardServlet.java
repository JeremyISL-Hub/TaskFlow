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

        request.setAttribute("tareas", tareas);
        request.getRequestDispatcher("dashboard.jsp").forward(request, response);
    }

}