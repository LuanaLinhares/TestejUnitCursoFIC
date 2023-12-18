/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.SistemaCursoFICmaster.test;

import com.example.SistemaCursoFICmaster.models.entity.Curso;
import com.example.SistemaCursoFICmaster.models.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {
    @Autowired
    private CursoRepository repository;

    public List<Curso> findAll(){
        return repository.findAll();
    }

    public Curso findById(Integer id){
        Optional<Curso> obj = repository.findById(id);
        return obj.get();
    }

    public Curso save(Curso obj){
        this.validate(obj);
        return repository.save(obj);
    }

    private void validate(Curso obj) {
        if (!obj.isValidFields()) {
            throw new IllegalArgumentException("Preencha todos os campos!");
        }
    }
}