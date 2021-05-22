package org.bootcamp.mycash.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.bootcamp.mycash.domain.Lancamento;
import org.bootcamp.mycash.repository.LancamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LancamentoService {

	@Autowired
	private LancamentoRepository repo;
	
	public List<Lancamento> todos() {		
		return repo.findAll();
	}
	
	public Lancamento apenasUm(Integer id) {
		return repo.findById(id)
				.orElseThrow(() -> new EntityNotFoundException());
	}
	
	public Lancamento criar(Lancamento lancamento) {
		return repo.save(lancamento);
	}
	
	public Lancamento atualizar(
			Integer id, 
			Lancamento novoLancamento) {
		return repo.findById(id).map(lancamento -> {
			lancamento.setDescricao(novoLancamento.getDescricao());
			lancamento.setValor(novoLancamento.getValor());
			lancamento.setTipo(novoLancamento.getTipo());
			lancamento.setData(novoLancamento.getData());
			
			return repo.save(lancamento);
		})
		.orElseThrow(() -> new EntityNotFoundException());
	}
	
	public void excluir(Integer id) {
		repo.deleteById(id);
	}
	
}
