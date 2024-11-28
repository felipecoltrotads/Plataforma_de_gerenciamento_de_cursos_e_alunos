package br.grupointegrado.faculdade.dto;

import br.grupointegrado.faculdade.model.Curso;
import br.grupointegrado.faculdade.model.Professor;

public record DisciplinaRequestDTO(Curso curso_id, Professor professor_id, String nome, String codigo) {
}
