<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.taskflow.taskflow.model.Tarea"%>
<%@page import="com.taskflow.taskflow.model.Usuario"%>
<%
    if (session.getAttribute("usuario") == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    Tarea tarea = (Tarea) request.getAttribute("tarea");

    if (tarea == null) {
        response.sendRedirect("dashboard");
        return;
    }
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Editar tarea</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/form.css">
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/favicon.png">
</head>
<body>
    <header>
        <div class="logo">TaskFlow</div>
        <nav>
            <a href="dashboard">Dashboard</a>
            <a href="logout">Cerrar sesión</a>
        </nav>
    </header>

    <main>
        <h1>Editar tarea</h1>

        <div class="contenedor-formulario">
            <form action="actualizarTarea" method="post">
                <input type="hidden" name="id" value="<%= tarea.getId() %>">

                <label>Título</label>
                <input type="text" name="titulo" value="<%= tarea.getTitulo() %>" required>

                <label>Descripción</label>
                <textarea name="descripcion" rows="5"><%= tarea.getDescripcion() %></textarea>

                <label>Estado</label>
                <select name="estado">
                    <option value="Pendiente" <%= tarea.getEstado().equals("Pendiente") ? "selected" : "" %>>
                        Pendiente
                    </option>
                    <option value="Completada" <%= tarea.getEstado().equals("Completada") ? "selected" : "" %>>
                        Completada
                    </option>
                </select>

                <label>Fecha límite</label>
                <input type="date" name="fechaLimite" value="<%= tarea.getFechaLimite() %>">

                <button type="submit">Guardar cambios</button>
            </form>
        </div>
    </main>
</body>
</html>