<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Iniciar Sesión - TaskFlow</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/form.css">
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/favicon.png">
</head>
<body>
    <header>
        <div class="logo">TaskFlow</div>
        <nav>
            <a href="index.jsp">Inicio</a>
            <a href="registro.jsp">Registrarse</a>
        </nav>
    </header>

    <main>
        <div class="contenedor-formulario">
            <h2>Iniciar Sesión</h2>

            <%
                String mensajeLogin = (String) session.getAttribute("mensajeLogin");
                if (mensajeLogin != null) {
            %>
            <div class="mensaje-exito">
                <%= mensajeLogin %>
            </div>
            <%
                    session.removeAttribute("mensajeLogin");
                }
            %>

            <form action="login" method="post">
                <label>Correo electrónico</label>
                <input type="email" name="correo" required>

                <label>Contraseña</label>
                <input type="password" name="password" required>

                <button type="submit">Ingresar</button>
            </form>

            <div class="registro-link">
                ¿No tienes una cuenta?
                <a href="registro.jsp">Regístrate aquí</a>
            </div>
        </div>
    </main>
</body>
</html>