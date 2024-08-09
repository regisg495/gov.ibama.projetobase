package br.gov.ibama.projetobase.repository;

import br.gov.ibama.projetobase.model.Pessoa;
import org.springframework.data.repository.CrudRepository;

public interface PessoaRepository extends CrudRepository<Pessoa, Long> {

}
