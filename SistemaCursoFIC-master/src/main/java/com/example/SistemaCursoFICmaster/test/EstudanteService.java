/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.SistemaCursoFICmaster.test;

import com.example.SistemaCursoFICmaster.models.entity.Estudante;
import com.example.SistemaCursoFICmaster.models.repository.EstudanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstudanteService {
    @Autowired
    private EstudanteRepository repository;

    public List<Estudante> findAll(){
        return repository.findAll();
    }

    public Estudante findById(Integer id){
        Optional<Estudante> obj = repository.findById(id);
        return obj.get();
    }

    public Estudante save(Estudante obj){
        this.validate(obj);
        return repository.save(obj);
    }

    private void validate(Estudante estudante) {

        //Verifica se os campos são validos
        if (!estudante.isValidFields()) {
            throw new IllegalArgumentException("Preencha todos os campos!");
        }

        // Verifica se tem no mínimo 15 anos completos
        if (!estudante.isOverFifteen()) {
            throw new IllegalArgumentException("Só serão aceitos estudantes que tenham no mínimo 15 anos completos.");
        }
    }
}