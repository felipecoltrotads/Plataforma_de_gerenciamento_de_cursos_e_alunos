package br.grupointegrado.faculdade.controller;

import br.grupointegrado.faculdade.dto.AlunoRequestDTO;
import br.grupointegrado.faculdade.model.Aluno;
import br.grupointegrado.faculdade.repository.AlunoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alunos")
public class AlunoController {
    @Autowired
    private AlunoRepository repository;
    @GetMapping
    public ResponseEntity<List<Aluno>> findAll(){
        return ResponseEntity.ok(this.repository.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Aluno> findById(@PathVariable Integer id) {
        Aluno aluno = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrando"));

        return ResponseEntity.ok(aluno);
    }
    @PostMapping
    public ResponseEntity<Aluno> save(@Valid @RequestBody AlunoRequestDTO dto) {
        Aluno aluno = new Aluno();
        aluno.setNome(dto.nome());
        aluno.setEmail(dto.email());
        aluno.setMatricula(dto.matricula());
        aluno.setData_nascimento(dto.data_nascimento());
        this.repository.save(aluno);

        return ResponseEntity.ok(aluno);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Aluno> update(@Valid @PathVariable Integer id,
                        @RequestBody AlunoRequestDTO dto){
        Aluno aluno = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado"));

        aluno.setNome(dto.nome());
        aluno.setEmail(dto.email());
        aluno.setMatricula(dto.matricula());
        aluno.setData_nascimento(dto.data_nascimento());
        this.repository.save(aluno);

        return ResponseEntity.ok(aluno);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        Aluno aluno = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado"));

        this.repository.delete(aluno);
        return ResponseEntity.noContent().build();
    }


}
