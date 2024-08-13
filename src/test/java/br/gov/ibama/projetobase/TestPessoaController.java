package br.gov.ibama.projetobase;

import br.gov.ibama.projetobase.controller.PessoaController;
import br.gov.ibama.projetobase.dto.PessoaDTO;
import br.gov.ibama.projetobase.service.impl.PessoaServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureJsonTesters
@WebMvcTest(PessoaController.class)
@WithMockUser
public class TestPessoaController {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PessoaServiceImpl service;

    @Autowired
    private JacksonTester<List<PessoaDTO>> listPessoaDToJsonConverter;

    @Autowired
    private JacksonTester<PessoaDTO> pessoaDToJsonConverter;

    @Test
    @DisplayName("Teste get/pessoa: Retornar todas pessoas cadastradas")
    public void getTodasPessoas() throws Exception {

        mockMvc.perform(get("/pessoa"))
                .andExpect(status().isOk())
                .andExpect(content().json(getListaPessoa()));
    }


    @Test
    @DisplayName("Teste get/pessoa/id: Retornar a pessoa referente ao id informado")
    public void getPessoaInformada() throws Exception {

        mockMvc.perform(get("/pessoa/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(getPessoa()));
    }

    @BeforeEach
    public void preparaService(){
        when(service.getAll()).thenReturn(List.of(getPessoaDTO()));
        when(service.get(1l)).thenReturn(getPessoaDTO());
    }
    /**
     * @return
     * @throws IOException
     */
    private String getListaPessoa() throws IOException {
        return listPessoaDToJsonConverter
                .write(List.of(getPessoaDTO()))
                .getJson();
    }

    /**
     * @return
     * @throws IOException
     */
    private String getPessoa() throws IOException {
        return pessoaDToJsonConverter
                .write(getPessoaDTO())
                .getJson();
    }

    /**
     * @return
     */
    private PessoaDTO getPessoaDTO() {
        return new PessoaDTO(1l, "00000000000", 'F');
    }
}
