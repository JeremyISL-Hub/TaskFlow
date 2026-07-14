package com.taskflow.taskflow.model;

import java.sql.Timestamp;
import java.util.UUID;

public class Usuario {

    private UUID id;
    private String nombre;
    private String correo;
    private String password;
    private Timestamp fechaRegistro;

    // Constructor vacío
    public Usuario() {
    }

    // Constructor sin ID (para registrar un nuevo usuario)
    public Usuario(String nombre, String correo, String password) {
        this.nombre = nombre;
        this.correo = correo;
        this.password = password;
    }

    // Constructor completo
    public Usuario(UUID id, String nombre, String correo, String password, Timestamp fechaRegistro) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.password = password;
        this.fechaRegistro = fechaRegistro;
    }

    // Getters y Setters

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Timestamp fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

}