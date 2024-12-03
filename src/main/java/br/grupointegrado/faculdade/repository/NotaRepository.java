package br.grupointegrado.faculdade.repository;

import br.grupointegrado.faculdade.model.Nota;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotaRepository extends JpaRepository<Nota, Integer> {
    List<Nota> findByMatricula_Aluno_Id(Integer aluno_id);

    List<Nota> findByMatricula_Turma_Id(Integer turma_id);

    List<Nota> findByDisciplina_Id(Integer disciplina_id);
}
