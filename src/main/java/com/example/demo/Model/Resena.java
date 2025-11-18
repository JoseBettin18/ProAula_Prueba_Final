package com.example.demo.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "resenas")
public class Resena {

    @Id
    private String id;
    private String lugarId;
    private String usuarioNombre;
    private String comentario;
    private int calificacion;
    private LocalDateTime fecha;

    public Resena() {
    }

    public Resena(String lugarId, String usuarioNombre, String comentario, int calificacion, LocalDateTime fecha) {
        this.lugarId = lugarId;
        this.usuarioNombre = usuarioNombre;
        this.comentario = comentario;
        this.calificacion = calificacion;
        this.fecha = fecha;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLugarId() {
        return lugarId;
    }

    public void setLugarId(String lugarId) {
        this.lugarId = lugarId;
    }

    public String getUsuarioNombre() {
        return usuarioNombre;
    }

    public void setUsuarioNombre(String usuarioNombre) {
        this.usuarioNombre = usuarioNombre;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
}
