package br.grupointegrado.faculdade.repository;

import br.grupointegrado.faculdade.model.Nota;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotaRepository extends JpaRepository<Nota, Integer> {
    List<Nota> findByMatricula_Aluno_id(Integer aluno_id);

    List<Nota> findByMatricula_Turma_id(Integer turma_id);

    List<Nota> findByDisciplina_id(Integer disciplina_id);
}
