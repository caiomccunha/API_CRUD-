package com.example.atividadeApi2.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.atividadeApi2.Model.AlunoModel;
import com.example.atividadeApi2.Repository.AlunoRepository;

@Service
public class AlunoService{
    
    @Autowired
    private AlunoRepository repository;

    public List <AlunoModel> listarTodos(){
        return repository.findAll();
    }
    public Optional <AlunoModel> buscarPorId(Long idAluno){
        return repository.findById(idAluno);
    }

    public AlunoModel salvar (AlunoModel alunoModels){
        return repository.save(alunoModels);
    }

    public void deletarAluno(Long idAluno){
        repository.deleteById(idAluno);
    }
}
