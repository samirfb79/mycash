package org.bootcamp.mycash.repository;

import org.bootcamp.mycash.domain.Lancamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LancamentoRepository extends JpaRepository<Lancamento, Integer> {

}
