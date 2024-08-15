package br.gov.ibama.projetobase.controller;

import br.gov.ibama.projetobase.dto.PessoaDTO;
import br.gov.ibama.projetobase.service.impl.PessoaServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    private static final Logger logger = LoggerFactory.getLogger(PessoaController.class);

    @Autowired
    private PessoaServiceImpl pessoaService;

    @GetMapping
    public ResponseEntity<List<PessoaDTO>> get() {
        return ResponseEntity.ok(pessoaService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaDTO> get(@PathVariable( value = "id") Long id){
        return ResponseEntity.ok(pessoaService.get(id));
    }

    @PostMapping
    public ResponseEntity<PessoaDTO> post(@RequestBody PessoaDTO pessoaDTO) {
        PessoaDTO pessoaDTONew = pessoaService.add(pessoaDTO);
        return ResponseEntity.ok(pessoaDTONew);
    }

    @PutMapping
    public ResponseEntity<PessoaDTO> update(@RequestBody PessoaDTO pessoaDTO) {
        PessoaDTO pessoaDTOUpdated = pessoaService.update(pessoaDTO);
        return ResponseEntity.ok(pessoaDTOUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        pessoaService.delete(id);
        return ResponseEntity.ok().build();
    }

    /**
     * Este´é um exemplo de logs
     * @return
     */
    @GetMapping("/exemplo/log")
    public ResponseEntity<List<PessoaDTO>> getLogExample() {
        logger.debug("Debug level - Hello Logback");
        logger.info("Info level - Hello Logback");
        logger.error("Error level - Hello Logback");
        logger.error("Error level - Hello Logback {}", "sua variavel aqui");

        return ResponseEntity.ok(pessoaService.getAll());
    }

}
