package br.grupointegrado.faculdade.repository;

import br.grupointegrado.faculdade.model.Nota;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotaRepository extends JpaRepository<Nota, Integer> {
}
