package br.grupointegrado.faculdade.controller;

import br.grupointegrado.faculdade.dto.ProfessorRequestDTO;
import br.grupointegrado.faculdade.model.Curso;
import br.grupointegrado.faculdade.model.Disciplina;
import br.grupointegrado.faculdade.model.Professor;
import br.grupointegrado.faculdade.repository.DisciplinaRepository;
import br.grupointegrado.faculdade.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/professores")
public class ProfessorController {
    @Autowired
    private ProfessorRepository repository;

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @GetMapping
    public ResponseEntity<List<Professor>> findAll(){
        return ResponseEntity.ok(this.repository.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Professor> findById(@PathVariable Integer id) {
        Professor professor = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Professor não encontrando"));

        return ResponseEntity.ok(professor);
    }
    @PostMapping
    public ResponseEntity<Professor> save(@RequestBody ProfessorRequestDTO dto) {
        Professor professor  = new Professor();
        professor.setNome(dto.nome());
        professor.setEmail(dto.email());
        professor.setTelefone(dto.telefone());
        professor.setEspecialidade(dto.especialidade());
        this.repository.save(professor);

        return ResponseEntity.ok(professor);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Professor> update(@PathVariable Integer id,
                        @RequestBody ProfessorRequestDTO dto){
        Professor professor = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Professor não encontrado"));

        professor.setNome(dto.nome());
        professor.setEmail(dto.email());
        professor.setTelefone(dto.telefone());
        professor.setEspecialidade(dto.especialidade());
        this.repository.save(professor);

        return ResponseEntity.ok(professor);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        Professor professor = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Professor não encontrado"));

        this.repository.delete(professor);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/add-disciplina")
    public ResponseEntity<Professor> addDisplina(@PathVariable Integer id,
                                             @RequestBody Disciplina disciplina){
        Professor professor = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Professor não encontrado"));
        disciplina.setProfessor(professor);
        this.disciplinaRepository.save(disciplina);

        return ResponseEntity.ok(professor);
    }


}
