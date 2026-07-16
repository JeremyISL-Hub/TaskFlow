package com.taskflow.taskflow.model;

import java.sql.Timestamp;
import java.util.UUID;

public class Tarea {

    private UUID id;
    private String titulo;
    private String descripcion;
    private String estado;
    private Timestamp fechaLimite;
    private Timestamp fechaCreacion;
    private UUID usuarioId;

    // Constructor vacío
    public Tarea() {
    }

    // Constructor para crear una nueva tarea
    public Tarea(String titulo, String descripcion, String estado, Timestamp fechaLimite, UUID usuarioId) {

        this.titulo = titulo;
        this.descripcion = descripcion;
        this.estado = estado;
        this.fechaLimite = fechaLimite;
        this.usuarioId = usuarioId;
    }

    public Tarea(UUID id, String titulo, String descripcion, String estado, Timestamp fechaLimite, Timestamp fechaCreacion, UUID usuarioId) {

        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.estado = estado;
        this.fechaLimite = fechaLimite;
        this.fechaCreacion = fechaCreacion;
        this.usuarioId = usuarioId;
    }

    // Getters y Setters

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Timestamp getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(Timestamp fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    public Timestamp getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Timestamp fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public UUID getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(UUID usuarioId) {
        this.usuarioId = usuarioId;
    }

}