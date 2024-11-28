package br.grupointegrado.faculdade.dto;

import br.grupointegrado.faculdade.model.Curso;

public record TurmaRequestDTO(Integer ano, Integer semestre, Curso curso_id) {
}
