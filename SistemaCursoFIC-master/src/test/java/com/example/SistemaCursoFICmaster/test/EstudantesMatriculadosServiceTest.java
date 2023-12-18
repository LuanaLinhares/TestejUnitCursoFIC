/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.SistemaCursoFICmaster.test;

import com.example.SistemaCursoFICmaster.models.entity.*;
import com.example.SistemaCursoFICmaster.models.repository.EstudantesMatriculadosRepository;
import com.example.SistemaCursoFICmaster.models.repository.TurmaCursoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EstudantesMatriculadosServiceTest {

    @Mock
    private EstudantesMatriculadosRepository repository;

    @InjectMocks
    private EstudantesMatriculadosService service;

    @Mock
    private TurmaCursoRepository turmaRepository;

    @Test
    public void testarSalvarComCamposValidos() {
        EstudantesMatriculados obj = criarEstudantesMatriculadosComValoresValidos(); // EstudantesMatriculados com todos os campos

        // Configurar o comportamento do repository.save mock
        when(repository.save(obj)).thenReturn(obj);
        when(turmaRepository.save(obj.getTurmaCurso())).thenReturn(obj.getTurmaCurso());

        EstudantesMatriculados objSaved = service.save(obj);

        assertNotNull(objSaved);
    }

    @Test
    public void testarMatricularAlunoForaDoPeriodoDeMatricula() {
        EstudantesMatriculados obj = criarEstudantesMatriculadosComValoresValidos(); // EstudantesMatriculados com todos os campos
        obj.setDataMatricula(LocalDate.of(2024, 11, 30)); // Change enrollment date to November 30, 2023

        assertThrows(IllegalArgumentException.class, () -> service.save(obj));
    }


    private EstudantesMatriculados criarEstudantesMatriculadosComValoresValidos() {
        LocalDate date = LocalDate.of(2000, 11, 25); // Modify birthdate to November 25, 2000

        Estudante estudante = new Estudante("Maria", "12345", "Brasília", "maria@example.com", date); // Change student details
        List<Celular> celulares = new ArrayList<>();
        celulares.add(new Celular("987654321")); // Update phone number
        estudante.setCelulares(celulares);

        List<Estudante> estudantes = new ArrayList<>();
        estudantes.add(estudante);

        Curso curso = new Curso("Engenharia", "16h", "Curso de engenharia"); // Change course details

        TurmaCurso turma = new TurmaCurso("Sala 101", 80, // Modify classroom and available slots
                LocalDate.of(2023, 1, 15), LocalDate.of(2023, 3, 15), // Update start and end dates
                LocalDate.of(2023, 3, 16), LocalDate.of(2023, 12, 20), // Update enrollment period
                curso);

        EstudantesMatriculados matricula = new EstudantesMatriculados(LocalDate.now(), turma, estudante);

        List<EstudantesMatriculados> matriculas = new ArrayList<>();
        matriculas.add(matricula);

        turma.setEstudantesMatriculadoses(matriculas);
        return matricula;
    }
}
