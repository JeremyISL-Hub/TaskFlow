<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.taskflow.taskflow.model.Usuario"%>
<%
    if (session.getAttribute("usuario") == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    Usuario usuario = (Usuario) session.getAttribute("usuario");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Nueva Tarea - TaskFlow</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/form.css">
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/favicon.png">
</head>
<body>
    <header>
        <div class="logo">TaskFlow</div>
        <nav>
            <a href="dashboard">Dashboard</a>
            <a href="dashboard">Mis tareas</a>
            <a href="logout">Cerrar sesión</a>
        </nav>
    </header>

    <main>
        <h1>Nueva tarea</h1>
        <p class="subtitulo">
            Completa la información para registrar una nueva tarea.
        </p>

        <div class="contenedor-formulario">
            <form action="crearTarea" method="post">
                <label for="titulo">Título</label>
                <input type="text" id="titulo" name="titulo"
                       placeholder="Ejemplo: Terminar informe" required>

                <label for="descripcion">Descripción</label>
                <textarea id="descripcion" name="descripcion" rows="6"
                          placeholder="Describe la tarea..."></textarea>

                <label for="fechaLimite">Fecha límite</label>
                <input type="date" id="fechaLimite" name="fechaLimite">

                <button type="submit">Guardar tarea</button>
            </form>

            <div style="text-align:center;margin-top:20px;">
                <a href="dashboard">Volver al Dashboard</a>
            </div>
        </div>
    </main>
</body>
</html>