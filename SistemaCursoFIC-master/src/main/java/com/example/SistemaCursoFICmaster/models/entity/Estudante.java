/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.SistemaCursoFICmaster.models.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author compo
 */
@Entity
@Table(name = "tb_estudante")
public class Estudante implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;
    private String nome, matricula, endereco, emailInstitucional;
    private LocalDate dataNascimento;

    @OneToMany(mappedBy = "estudante", cascade = CascadeType.PERSIST)
    private List<EstudantesMatriculados> matriculas = new ArrayList();

    @OneToMany(mappedBy = "estudante", cascade = CascadeType.PERSIST)
    private List<Celular> celulares = new ArrayList();

    public Estudante(String nome, String matricula, String endereco, String emailInstitucional, LocalDate dataNascimento) {
        this.nome = nome;
        this.matricula = matricula;
        this.endereco = endereco;
        this.emailInstitucional = emailInstitucional;
        this.dataNascimento = dataNascimento;
    }

    public Estudante() {
    }
    
    public List<Celular> getCelulares() {
        return celulares;
    }

    public void setCelulares(List<Celular> celulares) {
        this.celulares = celulares;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getData() {
        return dataNascimento;
    }

    public void setData(LocalDate data) {
        this.dataNascimento = data;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEmailInstitucional() {
        return emailInstitucional;
    }

    public void setEmailInstitucional(String emailInstitucional) {
        this.emailInstitucional = emailInstitucional;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public List<EstudantesMatriculados> getMatriculas() {
        return matriculas;
    }

    public void setMatriculas(List<EstudantesMatriculados> matriculas) {
        this.matriculas = matriculas;
    }
    
    // Verifica se todos os campos são validos
    public boolean isValidFields() {
        if (nome == null || nome.isEmpty()) {
            return false;
        }
        if (matricula == null || matricula.isEmpty()) {
            return false;
        }
        if (endereco == null || endereco.isEmpty()) {
            return false;
        }
        if (emailInstitucional == null || emailInstitucional.isEmpty()) {
            return false;
        }
        if (dataNascimento == null) {
            return false;
        }
        if (celulares.isEmpty()) {
            return false;
        }
        return true;
    }
    
    //Verifica se o estudante tem pelo menos de 15 anos completos
    public boolean isOverFifteen() {
        LocalDate birthDate = this.dataNascimento;
        LocalDate today = LocalDate.now();
        
        // Diferença em anos entre a data de nascimento e hoje
        Period period = Period.between(birthDate, today);
        
        return period.getYears() >= 15;
    }
    

}
