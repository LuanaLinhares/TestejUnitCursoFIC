/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.SistemaCursoFICmaster.models.repository;

import com.example.SistemaCursoFICmaster.models.entity.EstudantesMatriculados;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EstudantesMatriculadosRepository extends JpaRepository<EstudantesMatriculados, Integer> {
}
