/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.SistemaCursoFICmaster.test;

import com.example.SistemaCursoFICmaster.models.entity.Celular;
import com.example.SistemaCursoFICmaster.models.entity.Estudante;
import com.example.SistemaCursoFICmaster.models.repository.EstudanteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class EstudanteServiceTest {
    @Mock
    private EstudanteRepository repository;

    @InjectMocks
    private EstudanteService service;

    @Test
    public void testeSalvarEstudanteComCamposInvalidos() {
        Estudante estudante = new Estudante(); // estudante com campos em falta

        // Exceção lançada ao salvar um estudante com campos inválidos
        assertThrows(IllegalArgumentException.class, () -> service.save(estudante));
    }

    @Test
    public void testeSalvarEstudanteMenorDeIdade() {
        Estudante estudante = new Estudante("João", "12345", "Endereço", "joao@example.com", LocalDate.now().minusYears(14)); // estudante menor de idade

        // Exceção lançada ao salvar um estudante menor de idade
        assertThrows(IllegalArgumentException.class, () -> service.save(estudante));
    }

    @Test
    public void testeSalvarEstudanteMaiorDeIdade() {
        Estudante estudante = new Estudante("João", "12345", "Endereço", "joao@example.com", LocalDate.now().minusYears(18)); // estudante maior de idade
        Celular celular = new Celular("63981371158");
        List<Celular> celulares = new ArrayList<>();
        celulares.add(celular);
        estudante.setCelulares(celulares);

        // Configurar o comportamento do repository.save mock para um salvamento bem-sucedido
        when(repository.save(estudante)).thenReturn(estudante);

        Estudante estudanteSalvo = service.save(estudante);

        assertNotNull(estudanteSalvo);
    }

    @Test
    public void testeEncontrarTodosOsEstudantes() {
        // Configurar o comportamento do repository.findAll mock para retornar uma lista de estudantes
        List<Estudante> estudantes = Arrays.asList(
                new Estudante("João", "12345", "Endereço", "joao@example.com", LocalDate.now().minusYears(16)),
                new Estudante("Maria", "54321", "Outro Endereço", "maria@example.com", LocalDate.now().minusYears(17))
        );
        when(repository.findAll()).thenReturn(estudantes);

        List<Estudante> estudantesEncontrados = service.findAll();

        assertEquals(2, estudantesEncontrados.size());
    }

    @Test
    public void testeEncontrarEstudantePorIdExistente() {
        Estudante estudanteExistente = new Estudante("João", "12345", "Endereço", "joao@example.com", LocalDate.now().minusYears(16));
        when(repository.findById(1)).thenReturn(Optional.of(estudanteExistente));

        Estudante estudanteEncontrado = service.findById(1);

        assertNotNull(estudanteEncontrado);
        assertEquals("João", estudanteEncontrado.getNome());
    }

    @Test
    public void testeEncontrarEstudantePorIdInexistente() {
        when(repository.findById(1)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> service.findById(1));
    }
}
