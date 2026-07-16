<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>TaskFlow</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/index.css">
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/favicon.png">
</head>
<body>
    <header>
        <div class="logo">TaskFlow</div>
        <nav>
            <a href="index.jsp">Inicio</a>
            <a href="login.jsp">Iniciar sesión</a>
            <a href="registro.jsp">Registrarse</a>
        </nav>
    </header>

    <main>
        <section class="hero">
            <div class="hero-text">
                <h1>Organiza tus tareas de forma inteligente</h1>
                <p>
                    TaskFlow es una aplicación web desarrollada para ayudarte a
                    administrar tareas, proyectos y actividades diarias desde un
                    solo lugar.
                </p>
                <a href="registro.jsp" class="boton">Comenzar ahora</a>
            </div>
            <div class="hero-image">📋</div>
        </section>

        <section class="features">
            <div class="feature-card">
                <div class="icono">📋</div>
                <h3>Organiza</h3>
                <p>Mantén todas tus tareas ordenadas en un solo lugar.</p>
            </div>

            <div class="feature-card">
                <div class="icono">⏰</div>
                <h3>Prioriza</h3>
                <p>Establece fechas límite y administra mejor tu tiempo.</p>
            </div>

            <div class="feature-card">
                <div class="icono">📈</div>
                <h3>Avanza</h3>
                <p>Visualiza el progreso de tus actividades diariamente.</p>
            </div>
        </section>
    </main>

    <footer>
        <h3>TaskFlow</h3>
        <p>Sistema de gestión de tareas desarrollado con Java Web (Jakarta EE).</p>
        <p>Proyecto de Programación Orientada a Objetos</p>
        <p>© 2026</p>
    </footer>
</body>
</html>