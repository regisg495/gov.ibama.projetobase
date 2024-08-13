package br.gov.ibama.projetobase.service.impl;

import br.gov.ibama.projetobase.dto.PessoaDTO;
import br.gov.ibama.projetobase.model.Pessoa;
import br.gov.ibama.projetobase.repository.PessoaRepository;
import br.gov.ibama.projetobase.service.PessoaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PessoaServiceImpl implements PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PessoaDTO get(Long id) {
        Optional<Pessoa> pessoaOptional = pessoaRepository.findById(id);
        return pessoaOptional.map(this::convertToDto).orElse(null);
     }

    @Override
    public List<PessoaDTO> getAll() {
        List<PessoaDTO> pessoaDTO = new ArrayList<>();
         pessoaRepository.findAll()
                .forEach(e -> pessoaDTO.add(convertToDto(e)));
        return pessoaDTO;
    }

    private PessoaDTO convertToDto(Pessoa pessoa) {
        return modelMapper.map(pessoa, PessoaDTO.class);
    }

    private Pessoa convertToEntity(PessoaDTO pessoaDTO)   {
        return modelMapper.map(pessoaDTO, Pessoa.class);
    }

    @Transactional
    @Override
    public PessoaDTO add(PessoaDTO pessoaDTO) {
        return convertToDto(pessoaRepository.save(convertToEntity(pessoaDTO)));
    }

    @Transactional
    @Override
    public PessoaDTO update(PessoaDTO pessoaDTO) {
        return convertToDto(pessoaRepository.save(convertToEntity(pessoaDTO)));
    }

    @Override
    public void delete(Long id) {
        pessoaRepository.deleteById(id);
    }

}
