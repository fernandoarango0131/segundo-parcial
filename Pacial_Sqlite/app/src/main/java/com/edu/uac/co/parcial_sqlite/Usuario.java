package com.edu.uac.co.parcial_sqlite;

import java.io.Serializable;

public class Usuario implements Serializable {

    public String documento;
    public String nombre;
    public String estrato;
    public String salario;
    public String nivelEducativo;

    public Usuario(String documento, String nombre, String estrato, String salario, String nivelEducativo) {
        this.documento = documento;
        this.nombre = nombre;
        this.estrato = estrato;
        this.salario = salario;
        this.nivelEducativo = nivelEducativo;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstrato() {
        return estrato;
    }

    public void setEstrato(String estrato) {
        this.estrato = estrato;
    }

    public String getSalario() {
        return salario;
    }

    public void setSalario(String salario) {
        this.salario = salario;
    }

    public String getNivelEducativo() {
        return nivelEducativo;
    }

    public void setNivelEducativo(String nivelEducativo) {
        this.nivelEducativo = nivelEducativo;
    }
}
