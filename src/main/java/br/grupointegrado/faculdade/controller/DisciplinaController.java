package br.grupointegrado.faculdade.controller;

import br.grupointegrado.faculdade.dto.DisciplinaRequestDTO;
import br.grupointegrado.faculdade.model.Curso;
import br.grupointegrado.faculdade.model.Disciplina;
import br.grupointegrado.faculdade.model.Professor;
import br.grupointegrado.faculdade.repository.CursoRepository;
import br.grupointegrado.faculdade.repository.DisciplinaRepository;
import br.grupointegrado.faculdade.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/disciplinas")
public class DisciplinaController {

    @Autowired
    private DisciplinaRepository repository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @GetMapping
    public ResponseEntity<List<Disciplina>> findAll(){
        return ResponseEntity.ok(this.repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Disciplina> findById(@PathVariable Integer id) {
        Disciplina disciplina = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Displina não encontrando"));

        return ResponseEntity.ok(disciplina);
    }
    @PostMapping
    public ResponseEntity<Disciplina> save(@RequestBody DisciplinaRequestDTO dto) {
        Curso curso = this.cursoRepository.findById(dto.curso_id())
                .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado"));
        Professor professor = this.professorRepository.findById(dto.professor_id())
                .orElseThrow(() -> new IllegalArgumentException("Professor não encontrado"));

        Disciplina disciplina = new Disciplina();
        disciplina.setCurso(curso);
        disciplina.setProfessor(professor);
        disciplina.setNome(dto.nome());
        disciplina.setCodigo(dto.codigo());
        repository.save(disciplina);

        return ResponseEntity.ok(disciplina);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Disciplina> update(@PathVariable Integer id,
                                        @RequestBody DisciplinaRequestDTO dto){
        Disciplina disciplina = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Disciplina não encontrado"));
        Curso curso = this.cursoRepository.findById(dto.curso_id())
                .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado"));
        Professor professor = this.professorRepository.findById(dto.professor_id())
                .orElseThrow(() -> new IllegalArgumentException("Professor não encontrado"));

        disciplina.setCurso(curso);
        disciplina.setProfessor(professor);
        disciplina.setNome(dto.nome());
        disciplina.setCodigo(dto.codigo());
        this.repository.save(disciplina);

        return ResponseEntity.ok(disciplina);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        Disciplina disciplina = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Disciplina não encontrado"));

        this.repository.delete(disciplina);
        return ResponseEntity.noContent().build();
    }
}
