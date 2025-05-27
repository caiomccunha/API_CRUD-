package com.example.atividadeApi2.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.atividadeApi2.Model.AlunoModel;

@Repository
public interface AlunoRepository extends JpaRepository <AlunoModel, Long> {
}