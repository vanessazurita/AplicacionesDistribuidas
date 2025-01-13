package com.espe.cursos.models.entities;

import jakarta.persistence.*;

@Entity
@Table(name="cursos")

public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column (name = "name")
    private String nombre;
    private String description;
    private int creditos;

    //get

    public long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescription() {
        return description;
    }

    public int getCreditos() {
        return creditos;
    }

    //set


    public void setId(long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }
}