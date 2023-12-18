/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.SistemaCursoFICmaster.models.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author compo
 */
@Entity
@Table(name = "tb_curso")
public class Curso implements Serializable{
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;
    private String nome, cargaHoraria, descricao;
    
    @OneToMany(mappedBy = "curso", cascade = CascadeType.PERSIST)
    private List<TurmaCurso> turmas = new ArrayList();

    public Curso(String nome, String cargaHoraria, String descricao) {
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
        this.descricao = descricao;
    }

    public Curso() {
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(String cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<TurmaCurso> getTurmas() {
        return turmas;
    }

    public void setTurmas(List<TurmaCurso> turmas) {
        this.turmas = turmas;
    }
    
    public boolean isValidFields() {
    if (nome == null || nome.isEmpty()) {
        return false;
    }
    if (cargaHoraria == null || cargaHoraria.isEmpty()) {
        return false;
    }
    if (descricao == null || descricao.isEmpty()) {
        return false;
    }
    return true;
}

}
