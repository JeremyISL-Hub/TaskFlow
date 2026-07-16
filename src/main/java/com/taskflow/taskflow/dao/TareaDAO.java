package com.taskflow.taskflow.dao;

import com.taskflow.taskflow.model.Tarea;
import com.taskflow.taskflow.util.ConexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TareaDAO {

    private Connection obtenerConexion() {
        return ConexionBD.getConnection();
    }

    public boolean crearTarea(Tarea tarea) {
        String sql = """
                INSERT INTO tareas
                (titulo, descripcion, estado, fecha_limite, usuario_id)
                VALUES (?, ?, ?, ?, ?)
                """;

        try (Connection conexion = obtenerConexion();
                PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, tarea.getTitulo());
            ps.setString(2, tarea.getDescripcion());
            ps.setString(3, tarea.getEstado());
            ps.setDate(4, tarea.getFechaLimite());
            ps.setObject(5, tarea.getUsuarioId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Tarea> listarTareas(UUID usuarioId) {
        List<Tarea> tareas = new ArrayList<>();

        String sql = """
                SELECT *
                FROM tareas
                WHERE usuario_id = ?
                ORDER BY fecha_creacion DESC
                """;

        try (Connection conexion = obtenerConexion();
                PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setObject(1, usuarioId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Tarea tarea = new Tarea();
                tarea.setId((UUID) rs.getObject("id"));
                tarea.setTitulo(rs.getString("titulo"));
                tarea.setDescripcion(rs.getString("descripcion"));
                tarea.setEstado(rs.getString("estado"));
                tarea.setFechaLimite(rs.getDate("fecha_limite"));
                tarea.setFechaCreacion(rs.getTimestamp("fecha_creacion"));
                tarea.setUsuarioId((UUID) rs.getObject("usuario_id"));
                tareas.add(tarea);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tareas;
    }

    public Tarea buscarPorId(UUID id) {
        String sql = """
                SELECT *
                FROM tareas
                WHERE id = ?
                """;

        try (Connection conexion = obtenerConexion();
                PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setObject(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Tarea tarea = new Tarea();
                tarea.setId((UUID) rs.getObject("id"));
                tarea.setTitulo(rs.getString("titulo"));
                tarea.setDescripcion(rs.getString("descripcion"));
                tarea.setEstado(rs.getString("estado"));
                tarea.setFechaLimite(rs.getDate("fecha_limite"));
                tarea.setFechaCreacion(rs.getTimestamp("fecha_creacion"));
                tarea.setUsuarioId((UUID) rs.getObject("usuario_id"));
                return tarea;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Tarea buscarPorIdYUsuario(UUID tareaId, UUID usuarioId) {
        String sql = """
                SELECT *
                FROM tareas
                WHERE id = ?
                AND usuario_id = ?
                """;

        try (Connection conexion = obtenerConexion();
                PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setObject(1, tareaId);
            ps.setObject(2, usuarioId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Tarea tarea = new Tarea();
                tarea.setId((UUID) rs.getObject("id"));
                tarea.setTitulo(rs.getString("titulo"));
                tarea.setDescripcion(rs.getString("descripcion"));
                tarea.setEstado(rs.getString("estado"));
                tarea.setFechaLimite(rs.getDate("fecha_limite"));
                tarea.setFechaCreacion(rs.getTimestamp("fecha_creacion"));
                tarea.setUsuarioId((UUID) rs.getObject("usuario_id"));
                return tarea;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean actualizarTarea(Tarea tarea) {
        String sql = """
                UPDATE tareas
                SET titulo = ?,
                    descripcion = ?,
                    estado = ?,
                    fecha_limite = ?
                WHERE id = ?
                """;

        try (Connection conexion = obtenerConexion();
                PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, tarea.getTitulo());
            ps.setString(2, tarea.getDescripcion());
            ps.setString(3, tarea.getEstado());
            ps.setDate(4, tarea.getFechaLimite());
            ps.setObject(5, tarea.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean eliminarTarea(UUID id) {
        String sql = "DELETE FROM tareas WHERE id = ?";

        try (Connection conexion = obtenerConexion();
                PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setObject(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean eliminarTarea(UUID tareaId, UUID usuarioId) {
        String sql = """
                DELETE FROM tareas
                WHERE id = ?
                AND usuario_id = ?
                """;

        try (Connection conexion = obtenerConexion();
                PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setObject(1, tareaId);
            ps.setObject(2, usuarioId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<Tarea> buscarPorTitulo(UUID usuarioId, String titulo) {
        List<Tarea> lista = new ArrayList<>();

        String sql = """
                SELECT *
                FROM tareas
                WHERE usuario_id = ?
                AND LOWER(titulo) LIKE LOWER(?)
                ORDER BY fecha_creacion DESC
                """;

        try (Connection conexion = obtenerConexion();
                PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setObject(1, usuarioId);
            ps.setString(2, "%" + titulo + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Tarea tarea = new Tarea();
                tarea.setId((UUID) rs.getObject("id"));
                tarea.setTitulo(rs.getString("titulo"));
                tarea.setDescripcion(rs.getString("descripcion"));
                tarea.setEstado(rs.getString("estado"));
                tarea.setFechaLimite(rs.getDate("fecha_limite"));
                tarea.setFechaCreacion(rs.getTimestamp("fecha_creacion"));
                tarea.setUsuarioId((UUID) rs.getObject("usuario_id"));
                lista.add(tarea);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public List<Tarea> buscarFiltrarOrdenar(UUID usuarioId, String buscar, String estado, String orden) {
        List<Tarea> lista = new ArrayList<>();

        StringBuilder sql = new StringBuilder("SELECT * FROM tareas WHERE usuario_id=?");

        if (buscar != null && !buscar.isBlank()) {
            sql.append(" AND LOWER(titulo) LIKE LOWER(?)");
        }

        if (estado != null && !estado.isBlank()) {
            sql.append(" AND estado=?");
        }

        if ("antiguas".equals(orden)) {
            sql.append(" ORDER BY fecha_creacion ASC");
        } else if ("fecha".equals(orden)) {
            sql.append(" ORDER BY fecha_limite ASC NULLS LAST");
        } else {
            sql.append(" ORDER BY fecha_creacion DESC");
        }

        try (Connection conexion = obtenerConexion();
                PreparedStatement ps = conexion.prepareStatement(sql.toString())) {

            int i = 1;
            ps.setObject(i++, usuarioId);

            if (buscar != null && !buscar.isBlank()) {
                ps.setString(i++, "%" + buscar + "%");
            }

            if (estado != null && !estado.isBlank()) {
                ps.setString(i++, estado);
            }

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Tarea tarea = new Tarea();
                tarea.setId((UUID) rs.getObject("id"));
                tarea.setTitulo(rs.getString("titulo"));
                tarea.setDescripcion(rs.getString("descripcion"));
                tarea.setEstado(rs.getString("estado"));
                tarea.setFechaLimite(rs.getDate("fecha_limite"));
                tarea.setFechaCreacion(rs.getTimestamp("fecha_creacion"));
                tarea.setUsuarioId((UUID) rs.getObject("usuario_id"));
                lista.add(tarea);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

}