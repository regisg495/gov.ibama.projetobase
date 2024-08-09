package br.gov.ibama.projetobase.service;

import br.gov.ibama.projetobase.dto.PessoaDTO;

import java.util.List;

public interface PessoaService {

    PessoaDTO get(Long id);

    List<PessoaDTO> getAll();

    PessoaDTO add(PessoaDTO pessoaDTO);

    PessoaDTO update(PessoaDTO pessoaDTO);

    void delete(Long id);
}
