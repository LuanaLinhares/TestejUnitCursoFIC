/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.SistemaCursoFICmaster.models.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author compo
 */
@Entity
@Table(name = "tb_estudantes_matriculados")
public class EstudantesMatriculados implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;
    private LocalDate dataMatricula;

    @ManyToOne
    @JoinColumn(name = "id_turma_curso")
    private TurmaCurso turmaCurso;

    @ManyToOne
    @JoinColumn(name = "id_estudante")
    private Estudante estudante;

    public EstudantesMatriculados(LocalDate dataMatricula, TurmaCurso turmaCurso, Estudante estudante) {
        this.dataMatricula = dataMatricula;
        this.turmaCurso = turmaCurso;
        this.estudante = estudante;
    }

    public EstudantesMatriculados() {
    }
    
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDataMatricula() {
        return dataMatricula;
    }

    public void setDataMatricula(LocalDate dataMatricula) {
        this.dataMatricula = dataMatricula;
    }

    public TurmaCurso getTurmaCurso() {
        return turmaCurso;
    }

    public void setTurmaCurso(TurmaCurso turmaCurso) {
        this.turmaCurso = turmaCurso;
    }

    public Estudante getEstudante() {
        return estudante;
    }

    public void setEstudante(Estudante estudante) {
        this.estudante = estudante;
    }

    // Verifica se esta no periodo de matricula 
    // e se existem vagas disponiveis na turma
    public boolean podeMatricular() {
        List<LocalDate> periodoMatricula = getTurmaCurso().getPeriodoMatriculas();
        LocalDate inicio = periodoMatricula.get(0);
        LocalDate fim = periodoMatricula.get(1);
        
        if (dataMatricula.isBefore(inicio) || dataMatricula.isAfter(fim)){
            return false;
        }
        
        if (turmaCurso.getVagasDisponiveis()<=0){
            return false;
        }
        
        return true;
    }

}
