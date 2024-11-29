package com.francesco.microservices.app.cursos.models.entity;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="cursos")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descripcion;
    private String url;
    private String thumbnail;
    private Float valoracion;
    private Integer numero_votos;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_creacion")
    private Date fechaCreacion;
    @PrePersist
    public void ponerFecha(){
        this.fechaCreacion = new Date();
    }

    public Float getValoracion() {
        return valoracion;
    }

    public void setValoracion(Float valoracion) {
        this.valoracion = valoracion;
        this.numero_votos = 1;
    }

    //actualizar valoracion mediante voto
    public void addVoto(Integer voto) {
        this.valoracion = (this.valoracion*this.numero_votos++ + voto)/this.numero_votos;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

}
