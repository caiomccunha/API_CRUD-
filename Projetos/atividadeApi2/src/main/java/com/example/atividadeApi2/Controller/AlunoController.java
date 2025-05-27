package com.example.atividadeApi2.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.atividadeApi2.Model.AlunoModel;
import com.example.atividadeApi2.Service.AlunoService;

@RestController
@RequestMapping("api/alunos")
public class AlunoController {

    @Autowired
    private AlunoService service;

    @GetMapping
    public List <AlunoModel> listartodos(){
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity <AlunoModel> buscarPorId(@PathVariable ("id") Long idAluno){
       return service.buscarPorId(idAluno).map(ResponseEntity :: ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping 
    public AlunoModel salvar (@RequestBody AlunoModel alunosModel){
        return service.salvar(alunosModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity <AlunoModel> atualizar (@PathVariable Long idAluno, @RequestBody AlunoModel alunoModel){
        if(!service.buscarPorId(idAluno).isPresent()){
            return ResponseEntity.notFound().build();
        }
        alunoModel.setId_Aluno(idAluno);
        return ResponseEntity.ok(service.salvar(alunoModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity <Void> deletarAluno (@PathVariable Long idAluno){
        if(!service.buscarPorId(idAluno).isPresent()){
            return ResponseEntity.notFound().build();
        }
        service.deletarAluno(idAluno);
        return ResponseEntity.noContent().build();
    }
}
