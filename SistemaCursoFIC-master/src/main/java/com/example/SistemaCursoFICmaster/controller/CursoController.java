/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.SistemaCursoFICmaster.controller;

import com.example.SistemaCursoFICmaster.models.entity.Curso;
import com.example.SistemaCursoFICmaster.test.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @author compo
 */
@RestController
@RequestMapping("curso")
public class CursoController {

    @Autowired
    private CursoService service;

    @GetMapping
    public ResponseEntity<List<Curso>> list() {
        List<Curso> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }
    
    @GetMapping(value = "/{id}")
    public ResponseEntity<Curso> findById(@PathVariable Integer id){
        Curso obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }
    
    @PostMapping
    public ResponseEntity<Curso> save(@RequestBody Curso obj) {
        Curso obj_saved = service.save(obj);
        return ResponseEntity.ok().body(obj_saved);
    }
}
