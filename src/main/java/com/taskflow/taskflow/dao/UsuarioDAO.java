package com.taskflow.taskflow.dao;

import com.taskflow.taskflow.model.Usuario;
import com.taskflow.taskflow.util.ConexionBD;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.taskflow.taskflow.util.PasswordUtil;

import java.sql.Connection;

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

}