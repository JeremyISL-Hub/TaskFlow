<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registro - TaskFlow</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/form.css">
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/favicon.png">
</head>
<body>
    <header>
        <div class="logo">TaskFlow</div>
        <nav>
            <a href="index.jsp">Inicio</a>
            <a href="login.jsp">Iniciar sesión</a>
        </nav>
    </header>

    <main>
        <div class="contenedor-formulario">
            <h2>Crear cuenta</h2>
            <p class="descripcion">
                Completa la información para comenzar a utilizar TaskFlow.
            </p>

            <form action="registro" method="post">
                <label for="nombre">Nombre completo</label>
                <input type="text" id="nombre" name="nombre" required>

                <label for="correo">Correo electrónico</label>
                <input type="email" id="correo" name="correo" required>

                <label for="password">Contraseña</label>
                <input type="password" id="password" name="password" required>

                <button type="submit">Crear cuenta</button>
            </form>

            <div class="login-link">
                ¿Ya tienes una cuenta?
                <a href="login.jsp">Inicia sesión</a>
            </div>
        </div>
    </main>
</body>
</html>