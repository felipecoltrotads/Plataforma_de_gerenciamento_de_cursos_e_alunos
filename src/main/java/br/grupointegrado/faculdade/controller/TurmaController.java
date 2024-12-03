package br.grupointegrado.faculdade.controller;

import br.grupointegrado.faculdade.dto.TurmaRequestDTO;
import br.grupointegrado.faculdade.model.Curso;
import br.grupointegrado.faculdade.model.Turma;
import br.grupointegrado.faculdade.repository.CursoRepository;
import br.grupointegrado.faculdade.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/turmas")
public class TurmaController {

    @Autowired
    private TurmaRepository repository;

    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping
    public ResponseEntity<List<Turma>> findAll(){
        return ResponseEntity.ok(this.repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turma> findById(@PathVariable Integer id) {
        Turma turma = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Turma não encontrando"));

        return ResponseEntity.ok(turma);
    }
    @PostMapping
    public ResponseEntity<Turma> save(@RequestBody TurmaRequestDTO dto) {
        Curso curso = cursoRepository.findById(dto.curso_id())
                .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado"));
        Turma turma = new Turma();
        turma.setAno(dto.ano());
        turma.setSemestre(dto.semestre());
        turma.setCurso(curso);
        this.repository.save(turma);

        return ResponseEntity.ok(turma);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Turma> update(@PathVariable Integer id,
                                            @RequestBody TurmaRequestDTO dto){
        Turma turma = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Turma não encontrado"));
        Curso curso = cursoRepository.findById(dto.curso_id())
                .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado"));

        turma.setAno(dto.ano());
        turma.setSemestre(dto.semestre());
        turma.setCurso(curso);
        this.repository.save(turma);

        return ResponseEntity.ok(turma);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        Turma turma = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Turma não encontrado"));

        this.repository.delete(turma);
        return ResponseEntity.noContent().build();
    }

}
