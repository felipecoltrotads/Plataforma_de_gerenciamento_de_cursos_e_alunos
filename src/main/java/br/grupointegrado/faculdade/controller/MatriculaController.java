package br.grupointegrado.faculdade.controller;

import br.grupointegrado.faculdade.dto.MatriculaRequestDTO;
import br.grupointegrado.faculdade.model.Aluno;
import br.grupointegrado.faculdade.model.Matricula;
import br.grupointegrado.faculdade.model.Nota;
import br.grupointegrado.faculdade.model.Turma;
import br.grupointegrado.faculdade.repository.AlunoRepository;
import br.grupointegrado.faculdade.repository.MatriculaRepository;
import br.grupointegrado.faculdade.repository.NotaRepository;
import br.grupointegrado.faculdade.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matriculas")
public class MatriculaController {

    @Autowired
    private MatriculaRepository matriculaRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private TurmaRepository turmaRepository;

    private NotaRepository notaRepository;

    @GetMapping
    public ResponseEntity<List<Matricula>> findAll() {
        return ResponseEntity.ok(matriculaRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<Matricula> save(@RequestBody MatriculaRequestDTO dto) {
        Aluno aluno = alunoRepository.findById(dto.aluno_id())
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado"));
        Turma turma = turmaRepository.findById(dto.turma_id())
                .orElseThrow(() -> new IllegalArgumentException("Turma não encontrada"));

        // Verificar se o aluno já está matriculado na turma
        boolean exists;
        exists = matriculaRepository.existsByAlunoAndTurma(aluno, turma);
        if (exists) {
            throw new IllegalArgumentException("O aluno já está matriculado nesta turma");
        }

        Matricula matricula = new Matricula();
        matricula.setAluno(aluno);
        matricula.setTurma(turma);

        matriculaRepository.save(matricula);

        return ResponseEntity.ok(matricula);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        Matricula matricula = matriculaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Matrícula não encontrada"));

        matriculaRepository.delete(matricula);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/aluno/{aluno_id}/boletim")
    public ResponseEntity<List<Nota>> getBoletim(@PathVariable Integer aluno_id) {
        List<Nota> notas = notaRepository.findByMatricula_Aluno_id(aluno_id);
        return ResponseEntity.ok(notas);
    }

    @GetMapping("/turma/{turma_id}/desempenho")
    public ResponseEntity<List<Nota>> getDesempenhoPorTurma(@PathVariable Integer turma_id) {
        List<Nota> notas = notaRepository.findByMatricula_Turma_id(turma_id);
        return ResponseEntity.ok(notas);
    }

    @GetMapping("/disciplina/{disciplina_id}/desempenho")
    public ResponseEntity<List<Nota>> getDesempenhoPorDisciplina(@PathVariable Integer disciplina_id) {
        List<Nota> notas = notaRepository.findByDisciplina_id(disciplina_id);
        return ResponseEntity.ok(notas);
    }

}