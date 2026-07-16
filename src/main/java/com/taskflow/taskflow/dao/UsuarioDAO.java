package com.taskflow.taskflow.dao;

import com.taskflow.taskflow.model.Usuario;
import com.taskflow.taskflow.util.ConexionBD;

import com.taskflow.taskflow.util.PasswordUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class UsuarioDAO {

    public UsuarioDAO() {
    }

    private Connection obtenerConexion() {
        return ConexionBD.getConnection();
    }

    public boolean registrarUsuario(Usuario usuario) {
        
        String sql = "INSERT INTO usuarios (nombre, correo, password) VALUES (?, ?, ?)";

        try (Connection conexion = obtenerConexion();
                PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getCorreo());
            String passwordEncriptada = PasswordUtil.encriptar(usuario.getPassword());
            ps.setString(3, passwordEncriptada);

            int filas = ps.executeUpdate();
            
            return filas > 0;

        } catch (SQLException e) {
            
            e.printStackTrace();
            return false;
        }
    }

    public Usuario iniciarSesion(String correo, String password) {
        String sql = "SELECT * FROM usuarios WHERE correo = ?";

        try (Connection conexion = obtenerConexion();
                PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, correo);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String passwordGuardada = rs.getString("password");

                if (PasswordUtil.verificar(password, passwordGuardada)) {
                    Usuario usuario = new Usuario();
                    usuario.setId((UUID) rs.getObject("id"));
                    usuario.setNombre(rs.getString("nombre"));
                    usuario.setCorreo(rs.getString("correo"));
                    usuario.setPassword(passwordGuardada);
                    return usuario;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

}