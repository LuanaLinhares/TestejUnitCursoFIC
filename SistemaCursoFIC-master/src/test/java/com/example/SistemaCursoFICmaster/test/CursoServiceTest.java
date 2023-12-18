/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.SistemaCursoFICmaster.test;

import com.example.SistemaCursoFICmaster.models.entity.Curso;
import com.example.SistemaCursoFICmaster.models.repository.CursoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class CursoServiceTest {
    @Mock
    private CursoRepository repository;

    @InjectMocks
    private CursoService service;

    @Test
    public void testeSalvarComCamposValidosEFalhaNoSalvar() {
        Curso obj = new Curso("Sistemas", "20h", "Curso de sistemas"); // curso com todos os campos

        // Configurar o comportamento do repository.save mock para simular um salvamento falhado
        when(repository.save(obj)).thenThrow(RuntimeException.class);

        // Verificar se uma exceção é lançada ao salvar
        assertThrows(RuntimeException.class, () -> service.save(obj));
    }

    @Test
    public void testeEncontrarTodosOsCursos() {
        // Configurar o comportamento do repository.findAll mock para retornar uma lista de cursos
        List<Curso> cursos = Arrays.asList(
                new Curso("Sistemas", "20h", "Curso de sistemas"),
                new Curso("Redes", "90h", "Curso de redes")
        );
        when(repository.findAll()).thenReturn(cursos);

        List<Curso> cursosEncontrados = service.findAll();

        assertEquals(2, cursosEncontrados.size());
    }

    @Test
    public void testeEncontrarCursoPorIdExistente() {
        Curso cursoExistente = new Curso("Sistemas", "20h", "Curso de sistemas");
        when(repository.findById(1)).thenReturn(Optional.of(cursoExistente));

        Curso cursoEncontrado = service.findById(1);

        assertNotNull(cursoEncontrado);
        assertEquals("Sistemas", cursoEncontrado.getNome());
    }

    @Test
    public void testeEncontrarCursoPorIdInexistente() {
        when(repository.findById(1)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> service.findById(1));
    }
}
