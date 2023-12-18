/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.SistemaCursoFICmaster.controller;

import com.example.SistemaCursoFICmaster.models.entity.Estudante;
import com.example.SistemaCursoFICmaster.test.EstudanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @author compo
 */
@RestController
@RequestMapping("estudante")
public class EstudanteController {

    @Autowired
    private EstudanteService service;

    @GetMapping
    public ResponseEntity<List<Estudante>> list() {
        List<Estudante> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }
    
    @GetMapping(value = "/{id}")
    public ResponseEntity<Estudante> findById(@PathVariable Integer id){
        Estudante obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }
    
    @PostMapping
    public ResponseEntity<Estudante> save(@RequestBody Estudante estudante) {
        Estudante obj = service.save(estudante);
        return ResponseEntity.ok().body(obj);
    }
}
