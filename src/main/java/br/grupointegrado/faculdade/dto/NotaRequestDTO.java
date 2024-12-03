package br.grupointegrado.faculdade.dto;

import java.util.Date;

public record NotaRequestDTO(Integer matricula_id, Integer disciplina_id, Double nota, Date data_lancamento) {
}
