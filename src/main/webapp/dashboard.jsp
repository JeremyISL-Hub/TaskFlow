<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.taskflow.taskflow.model.Usuario"%>
<%@page import="com.taskflow.taskflow.model.Tarea"%>
<%@page import="java.util.List"%>
<%
    // Verificar si existe una sesión iniciada
    if (session.getAttribute("usuario") == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    // Obtener el usuario de la sesión
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    List<Tarea> tareas = (List<Tarea>) request.getAttribute("tareas");

    int total = 0;
    int pendientes = 0;
    int completadas = 0;
    int porcentaje = 0;

    if (tareas != null) {
        total = tareas.size();

        for (Tarea tarea : tareas) {
            if ("Completada".equalsIgnoreCase(tarea.getEstado())) {
                completadas++;
            } else {
                pendientes++;
            }
        }

        if (total > 0) {
            porcentaje = (completadas * 100) / total;
        }
    }
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard - TaskFlow</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dashboard.css">
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/favicon.png">
</head>
<body>
    <header>
        <div class="logo">TaskFlow</div>
        <nav>
            <a href="dashboard">Inicio</a>
            <a href="#">Mis tareas</a>
            <a href="logout">Cerrar sesión</a>
        </nav>
    </header>

    <main>
        <h1>Bienvenido, <%= usuario.getNombre() %> 👋</h1>
        <p class="subtitulo">
            Organiza tus actividades de forma sencilla con TaskFlow.
        </p>

        <%
            String mensaje = (String) session.getAttribute("mensaje");
            if (mensaje != null) {
        %>
        <div class="mensaje-exito">
            <%= mensaje %>
        </div>
        <%
                session.removeAttribute("mensaje");
            }
        %>

        <div class="tarjetas">
            <div class="tarjeta">
                <h2>Total de tareas</h2>
                <div class="numero"><%= total %></div>
            </div>

            <div class="tarjeta">
                <h2>Pendientes</h2>
                <div class="numero"><%= pendientes %></div>
            </div>

            <div class="tarjeta">
                <h2>Completadas</h2>
                <div class="numero"><%= completadas %></div>
            </div>

            <div class="tarjeta">
                <h2>Avance</h2>
                <div class="numero"><%= porcentaje %>%</div>
            </div>
        </div>

        <div class="panel">
            <div class="panel-header">
                <h2>Mis tareas</h2>
                <a href="nuevaTarea.jsp">
                    <button class="btn-nueva">+ Nueva tarea</button>
                </a>
            </div>

            <!-- Buscador -->
            <div class="buscador">
                <form action="dashboard" method="get">
                    <input type="text" name="buscar" placeholder="Buscar por título..."
                           value="<%= request.getParameter("buscar") == null ? "" : request.getParameter("buscar") %>">

                    <select name="estado">
                        <option value="">Todos los estados</option>
                        <option value="Pendiente" <%= "Pendiente".equals(request.getParameter("estado")) ? "selected" : "" %>>
                            Pendientes
                        </option>
                        <option value="Completada" <%= "Completada".equals(request.getParameter("estado")) ? "selected" : "" %>>
                            Completadas
                        </option>
                    </select>

                    <select name="orden">
                        <option value="recientes" <%= request.getParameter("orden") == null || "recientes".equals(request.getParameter("orden")) ? "selected" : "" %>>
                            Más recientes
                        </option>
                        <option value="antiguas" <%= "antiguas".equals(request.getParameter("orden")) ? "selected" : "" %>>
                            Más antiguas
                        </option>
                        <option value="fecha" <%= "fecha".equals(request.getParameter("orden")) ? "selected" : "" %>>
                            Fecha límite
                        </option>
                    </select>

                    <button type="submit" class="btn-buscar">Buscar</button>
                </form>
            </div>

            <%
                if (tareas == null || tareas.isEmpty()) {
            %>
            <p>
                Aún no tienes tareas registradas.
                Comienza creando tu primera tarea.
            </p>
            <%
                } else {
                    for (Tarea tarea : tareas) {
            %>
            <div class="tarjeta">
                <h3><%= tarea.getTitulo() %></h3>
                <p><%= tarea.getDescripcion() %></p>
                <br>
                <%
                boolean vencida =
                        tarea.getFechaLimite() != null
                        && tarea.getFechaLimite().before(new java.util.Date())
                        && !"Completada".equalsIgnoreCase(tarea.getEstado());
                %>
                <strong>Estado:</strong>
                <%
                if ("Completada".equalsIgnoreCase(tarea.getEstado())) {
                %>

                <span class="estado completada">
                    ✅ Completada
                </span>

                <%
                } else if (vencida) {
                %>

                <span class="estado vencida">
                    ⏰ Vencida
                </span>

                <%
                } else {
                %>

                <span class="estado pendiente">
                    🟡 Pendiente
                </span>

                <%
                }
                %>
                <br><br>
                <strong>📅 Fecha límite:</strong> <%= new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm").format(tarea.getFechaLimite()) %>

                <div class="acciones">

                    <% if (!"Completada".equalsIgnoreCase(tarea.getEstado())) { %>

                        <form action="completarTarea" method="post" style="display:inline;">
                            <input type="hidden" name="id" value="<%= tarea.getId() %>">
                            <button type="submit" class="btn-completar">
                                ✅ Completar
                            </button>
                        </form>

                    <% } %>

                    <a href="editarTarea?id=<%= tarea.getId() %>">
                        <button type="button" class="btn-editar">
                            ✏️ Editar
                        </button>
                    </a>

                    <a href="eliminarTarea?id=<%= tarea.getId() %>"
                       onclick="return confirm('¿Estás seguro de eliminar esta tarea?');">
                        <button type="button" class="btn-eliminar">
                            🗑️ Eliminar
                        </button>
                    </a>

                </div>
            </div>
            <%
                    }
                }
            %>
        </div>
    </main>
</body>
</html>