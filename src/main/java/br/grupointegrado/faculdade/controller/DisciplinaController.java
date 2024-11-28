package br.grupointegrado.faculdade.controller;

import br.grupointegrado.faculdade.dto.DisciplinaRequestDTO;
import br.grupointegrado.faculdade.model.Disciplina;
import br.grupointegrado.faculdade.repository.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/displinas")
public class DisciplinaController {

    @Autowired
    private DisciplinaRepository repository;

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
        Disciplina disciplina = new Disciplina();
        disciplina.setCurso(dto.curso_id());
        disciplina.setProfessor(dto.professor_id());
        disciplina.setNome(dto.nome());
        disciplina.setCodigo(dto.codigo());
        this.repository.save(disciplina);

        return ResponseEntity.ok(disciplina);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Disciplina> update(@PathVariable Integer id,
                                        @RequestBody DisciplinaRequestDTO dto){
        Disciplina disciplina = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Disciplina não encontrado"));

        disciplina.setCurso(dto.curso_id());
        disciplina.setProfessor(dto.professor_id());
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
