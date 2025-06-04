package com.example.atividadeApi2.Controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin(origins = "*")

@RequestMapping("api/alunos")
public class AlunoController {

    private static final Logger logger = LoggerFactory.getLogger(AlunoController.class);
    @Autowired
    private AlunoService service;

    @GetMapping
    public List <AlunoModel> listartodos(){
        logger.info("🎯 [GET] Buscando aluno com ID = {}");
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity <AlunoModel> buscarPorId(@PathVariable Long id){
       return service.buscarPorId(id).map(ResponseEntity :: ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping 
    public AlunoModel salvar (@RequestBody AlunoModel alunosModel){
        return service.salvar(alunosModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity <AlunoModel> atualizar (@PathVariable Long id, @RequestBody AlunoModel alunoModel){
        if(!service.buscarPorId(id).isPresent()){
            return ResponseEntity.notFound().build();
        }
        alunoModel.setId(id);
        return ResponseEntity.ok(service.salvar(alunoModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity <Void> deletarAluno (@PathVariable Long id){
        logger.info("📢 Entrei aqui ------------------------------", id);
        if(!service.buscarPorId(id).isPresent()){
            logger.debug("ta rolando isso ",service.buscarPorId(id));
            logger.info("📢 Parei no IF1 ------------------------------", id);
            return ResponseEntity.notFound().build();
        }
        service.deletarAluno(id);
        logger.info("📢 Listando todos os alunos ------------------------------", id);
        return ResponseEntity.noContent().build();
    }
}
