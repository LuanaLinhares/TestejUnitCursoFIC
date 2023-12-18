/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.SistemaCursoFICmaster.models.entity;

import jakarta.persistence.*;

/**
 *
 * @author amanda
 */
@Entity
@Table(name = "tb_celular")
public class Celular {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;
    private String numero;

    @ManyToOne
    @JoinColumn(name = "id_estudante")
    private Estudante estudante;
    
    public Celular(String numero) {
        this.numero = numero;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Estudante getEstudante() {
        return estudante;
    }

    public void setEstudante(Estudante estudante) {
        this.estudante = estudante;
    }
    
    
}
