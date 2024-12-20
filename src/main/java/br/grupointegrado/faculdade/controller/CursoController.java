package br.grupointegrado.faculdade.controller;

import br.grupointegrado.faculdade.dto.CursoRequestDTO;
import br.grupointegrado.faculdade.model.Curso;
import br.grupointegrado.faculdade.model.Disciplina;
import br.grupointegrado.faculdade.model.Turma;
import br.grupointegrado.faculdade.repository.CursoRepository;
import br.grupointegrado.faculdade.repository.DisciplinaRepository;
import br.grupointegrado.faculdade.repository.TurmaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/cursos")
public class CursoController {

        @Autowired
        private CursoRepository repository;

        @Autowired
        private TurmaRepository turmarepository;

        @Autowired
        private DisciplinaRepository disciplinaRepository;

        @GetMapping
        public ResponseEntity<List<Curso>> findAll(){
            return ResponseEntity.ok(this.repository.findAll());
        }
        @GetMapping("/{id}")
        public ResponseEntity<Curso> findById(@PathVariable Integer id) {
            Curso curso = this.repository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Curso não encontrando"));

            return ResponseEntity.ok(curso);
        }
        @PostMapping
        public ResponseEntity<Curso> save(@Valid @RequestBody CursoRequestDTO dto) {
            Curso curso = new Curso();
            curso.setNome(dto.nome());
            curso.setCodigo(dto.codigo());
            curso.setCarga_horaria(dto.carga_horaria());
            this.repository.save(curso);

            return ResponseEntity.ok(curso);
        }
        @PutMapping("/{id}")
        public ResponseEntity<Curso> update(@Valid @PathVariable Integer id,
                            @RequestBody CursoRequestDTO dto){
            Curso curso = this.repository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado"));

            curso.setNome(dto.nome());
            curso.setCodigo(dto.codigo());
            curso.setCarga_horaria(dto.carga_horaria());
            this.repository.save(curso);

            return ResponseEntity.ok(curso);
        }
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> delete(@PathVariable Integer id){
            Curso curso = this.repository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado"));

            this.repository.delete(curso);
            return ResponseEntity.noContent().build();
        }

        @PostMapping("/{id}/add-turma")
        public ResponseEntity<Curso> addTurma(@PathVariable Integer id,
                                              @RequestBody Turma turma){
            Curso curso = this.repository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado"));
            turma.setCurso(curso);
            this.turmarepository.save(turma);

            return ResponseEntity.ok(curso);
        }

        @PostMapping("/{id}/add-disciplina")
        public ResponseEntity<Curso> addDisciplina(@PathVariable Integer id,
                                          @RequestBody Disciplina disciplina){
            Curso curso = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado"));
            disciplina.setCurso(curso);
            this.disciplinaRepository.save(disciplina);

            return ResponseEntity.ok(curso);
        }


}

