package com.example.inmoniliarialuceromoviles.modelo;

import java.io.Serializable;
import java.util.Objects;

public class Inmueble implements Serializable {
    private int id;
    private String direccion;
    private String tipo;
    private double precio;
    private boolean estado;
    private Propietario propietario;
    //En falso significa que el innmueble no está disponible por alguna falla en el mismo.
    private String imagen;

    public Inmueble(int id, String direccion, String tipo, int ambientes, double precio, Propietario propietario, boolean estado, String imagen) {
        this.id= id;
        this.direccion = direccion;
        this.tipo = tipo;
        this.precio = precio;
        this.estado = estado;
        this.propietario = propietario;
        this.imagen = imagen;
    }
    public Inmueble() {

    }
    public int getIdInmueble() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Propietario getPropietario() {
        return propietario;
    }

    public void setPropietario(Propietario propietario) {
        this.propietario = propietario;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Inmueble inmueble = (Inmueble) o;
        return id == inmueble.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
