/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.SistemaCursoFICmaster.test;

import com.example.SistemaCursoFICmaster.models.entity.*;
import com.example.SistemaCursoFICmaster.models.repository.TurmaCursoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class TurmaCursoServiceTest {
    @Mock
    private TurmaCursoRepository repository;

    @InjectMocks
    private TurmaCursoService service;

    @Test
    public void testarSalvarComCamposInvalidos() {
        TurmaCurso obj = new TurmaCurso(); // Turmacurso com campos em falta

        // Exceção lançada ao salvar um Turmacurso com campos inválidos
        assertThrows(IllegalArgumentException.class, () -> service.save(obj));
    }


    @Test
    public void testarPeriodoInvalidoParaMatricula() {
        // Criar uma turma com período de matrículas inválido
        Curso curso = new Curso("Engenharia", "16h", "Curso de engenharia"); // Updated course details

        TurmaCurso obj = new TurmaCurso("Sala 15", 90, // Updated classroom and available slots
                LocalDate.of(2023, 10, 1), LocalDate.of(2023, 12, 15), // Updated start and end dates
                LocalDate.of(2023, 11, 20), LocalDate.of(2024, 3, 20), // Updated enrollment period
                curso);

        // Verificar se a exceção é lançada ao salvar uma turma com período de matrículas inválido
        assertThrows(IllegalArgumentException.class, () -> service.save(obj));
    }

    @Test
    public void testarEncontrarTurmaComEstudantesMatriculados() {
        // Configurar o comportamento do repositório mock para retornar uma turma com estudantes matriculados
        when(repository.findById(1)).thenReturn(Optional.of(criarTurmaCursoComValoresValidos()));

        // Verificar se a turma com estudantes matriculados é retornada corretamente pelo findById
        TurmaCurso turma = service.findById(1);

        assertNotNull(turma);
    }

    @Test
    public void testarEncontrarTurmaSemEstudantesMatriculados() {
        // Configurar o comportamento do repositório mock para retornar uma turma sem estudantes matriculados
        TurmaCurso obj = criarTurmaCursoComValoresValidos();
        obj.setEstudantesMatriculadoses(new ArrayList<>());
        when(repository.findById(2)).thenReturn(Optional.of(obj));

        // Verificar se é lançada a exceção ao tentar encontrar uma turma sem estudantes matriculados
        assertThrows(IllegalArgumentException.class, () -> service.findById(2));
    }

    private TurmaCurso criarTurmaCursoComValoresValidos(){
        LocalDate date = LocalDate.of(2000, 11, 25); // Updated birthdate

        Estudante estudante = new Estudante("Lucas", "54321", "Rio de Janeiro", "lucas@example.com", date); // Updated student details
        List<Celular> celulares = new ArrayList<>();
        celulares.add(new Celular("987654321")); // Updated phone number
        estudante.setCelulares(celulares);

        List<Estudante> estudantes = new ArrayList<>();
        estudantes.add(estudante);

        Curso curso = new Curso("Direito", "18h", "Curso de direito"); // Updated course details

        TurmaCurso turma = new TurmaCurso("Sala 101", 80,
                LocalDate.of(2023, 1, 15), LocalDate.of(2023, 3, 15), // Update start and end dates
                LocalDate.of(2023, 3, 16), LocalDate.of(2023, 12, 20),
                curso);

        List<EstudantesMatriculados> matriculas = new ArrayList<>();
        matriculas.add(new EstudantesMatriculados(LocalDate.now(), turma, estudante));

        turma.setEstudantesMatriculadoses(matriculas);
        return turma;
    }
}
