package br.grupointegrado.faculdade.controller;

import br.grupointegrado.faculdade.dto.NotaRequestDTO;
import br.grupointegrado.faculdade.model.Disciplina;
import br.grupointegrado.faculdade.model.Matricula;
import br.grupointegrado.faculdade.model.Nota;
import br.grupointegrado.faculdade.repository.DisciplinaRepository;
import br.grupointegrado.faculdade.repository.MatriculaRepository;
import br.grupointegrado.faculdade.repository.NotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notas")
public class NotaController {

    @Autowired
    private NotaRepository notaRepository;

    @Autowired
    private MatriculaRepository matriculaRepository;

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @GetMapping
    public ResponseEntity<List<Nota>> findAll() {
        return ResponseEntity.ok(notaRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<Nota> save(@RequestBody NotaRequestDTO dto) {
        Matricula matricula = matriculaRepository.findById(dto.matricula_id())
                .orElseThrow(() -> new IllegalArgumentException("Matrícula não encontrada"));
        Disciplina disciplina = disciplinaRepository.findById(dto.disciplina_id())
                .orElseThrow(() -> new IllegalArgumentException("Disciplina não encontrada"));

        // Validar se a disciplina pertence ao curso da matrícula
        if (!disciplina.getCurso().equals(matricula.getTurma().getCurso())) {
            throw new IllegalArgumentException("Disciplina não pertence ao curso da matrícula");
        }

        Nota nota = new Nota();
        nota.setMatricula(matricula);
        nota.setDisciplina(disciplina);
        nota.setNota(dto.nota());
        nota.setData_lancamento(dto.data_lancamento());

        notaRepository.save(nota);

        return ResponseEntity.ok(nota);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Nota> update(@PathVariable Integer id, @RequestBody NotaRequestDTO dto) {
        Nota nota = notaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Nota não encontrada"));

        nota.setNota(dto.nota());
        nota.setData_lancamento(dto.data_lancamento());

        notaRepository.save(nota);

        return ResponseEntity.ok(nota);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        Nota nota = notaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Nota não encontrada"));

        notaRepository.delete(nota);

        return ResponseEntity.noContent().build();
    }
}