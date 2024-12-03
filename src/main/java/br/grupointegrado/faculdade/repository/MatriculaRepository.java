package br.grupointegrado.faculdade.repository;

import br.grupointegrado.faculdade.model.Aluno;
import br.grupointegrado.faculdade.model.Matricula;
import br.grupointegrado.faculdade.model.Turma;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatriculaRepository extends JpaRepository<Matricula, Integer> {
    boolean existsByAlunoAndTurma(Aluno aluno, Turma turma);
}
