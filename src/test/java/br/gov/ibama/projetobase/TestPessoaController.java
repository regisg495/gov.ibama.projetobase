package br.gov.ibama.projetobase;

import br.gov.ibama.projetobase.controller.PessoaController;
import br.gov.ibama.projetobase.dto.PessoaDTO;
import br.gov.ibama.projetobase.service.impl.PessoaServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.http.MediaType.APPLICATION_JSON;

import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.IOException;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
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
                .andExpect(content().json(getPessoaAsJson()));
    }

    @Test
    @DisplayName("Teste get/pessoa/id: Retorno de id inexistente")
    public void getPessoaInformadaInexistente() throws Exception {

        mockMvc.perform(get("/pessoa/2"))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    @DisplayName("Teste delete/pessoa: Remove uma pessoa ")
    public void deleteId() throws Exception {
        mockMvc.perform(delete("/pessoa/1")
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    @DisplayName("Teste delete/pessoa: Remove uma pessoa com com id  inexistente")
    public void deleteIdInexistente() throws Exception {
        mockMvc.perform(delete("/pessoa/2")
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }


    @Test
    @DisplayName("Teste post/pessoa: Insere uma nova pessoa")
    public void postPessoa() throws Exception {

        mockMvc.perform(
                        post("/pessoa")
                                .with(csrf())
                                .content(getPessoaAsJson())
                                .contentType(APPLICATION_JSON)
                                .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(getPessoaAsJson()));
    }

    /**
     * Atenção: este é um método de exemplo
     * Os recursos de checagem não formam implementados
     * @throws Exception
     */
    @Test
    @DisplayName("Teste post/pessoa: Insere uma nova pessoa com id previamente informado")
    public void postPessoaComId()  throws Exception {
        mockMvc.perform(
                        post("/pessoa")
                                .with(csrf())
                                .content(getPessoaAsJson())
                                .contentType(APPLICATION_JSON)
                                .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(getPessoaAsJson()));
    }

    /**
     * Atenção: este é um método de exemplo
     * Os recursos de checagem de CPF não formam implementados
     * @throws Exception
     */
    @Test
    @DisplayName("Teste post/pessoa: Insere uma nova pessoa com cpf duplicado")
    public void postPessoaComCpfDuplicado() throws Exception {
        mockMvc.perform(
                        post("/pessoa")
                                .with(csrf())
                                .content(getPessoaAsJson())
                                .contentType(APPLICATION_JSON)
                                .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(getPessoaAsJson()));
    }

    /**
     * Atenção: este é um método de exemplo
     * Os recursos de checagem de CPF não formam implementados
     * @throws Exception
     */
    @Test
    @DisplayName("Teste post/pessoa: Insere uma nova pessoa com cpf inválido")
    public void postPessoaComCpfInvalido() throws Exception {
        mockMvc.perform(
                        post("/pessoa")
                                .with(csrf())
                                .content(getPessoaAsJson())
                                .contentType(APPLICATION_JSON)
                                .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(getPessoaAsJson()));
    }


    @Test
    @DisplayName("Teste put/pessoa: Atualiza uma pessoa")
    public void atualizaPessoa() throws Exception {
        mockMvc.perform(
                        put("/pessoa")
                                .with(csrf())
                                .content(getPessoaAsJson())
                                .contentType(APPLICATION_JSON)
                                .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(getPessoaAsJson()));
    }

    /**
     * Atenção: este é um método de exemplo
     * Os recursos de checagem de CPF não formam implementados
     * @throws Exception
     */
    @Test
    @DisplayName("Teste put/pessoa: Atualiza uma pessoa com com CPF existente")
    public void xx3() throws Exception {
        mockMvc.perform(
                        put("/pessoa")
                                .with(csrf())
                                .content(getPessoaAsJson())
                                .contentType(APPLICATION_JSON)
                                .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(getPessoaAsJson()));
    }

    /**
     * Atenção: este é um método de exemplo
     * Os recursos de checagem de CPF não formam implementados
     * @throws Exception
     */
    @Test
    @DisplayName("Teste put/pessoa: Atualiza uma nova pessoa com cpf inválido")
    public void updatePessoaComCpfInvalido() throws Exception {
        mockMvc.perform(
                        put("/pessoa")
                                .with(csrf())
                                .content(getPessoaAsJson())
                                .contentType(APPLICATION_JSON)
                                .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(getPessoaAsJson()));
    }

    /**
     * Atenção: este é um método de exemplo
     * Os recursos de checagem de CPF não formam implementados
     * @throws Exception
     */
    @Test
    @DisplayName("Teste put/pessoa: Atualiza uma pessoa com com id  inexistente")
    public void xx1() throws Exception {
        mockMvc.perform(
                        put("/pessoa")
                                .with(csrf())
                                .content(getPessoaAsJson())
                                .contentType(APPLICATION_JSON)
                                .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(getPessoaAsJson()));
    }



    @BeforeEach
    public void preparaService(){
        when(service.getAll()).thenReturn(List.of(getPessoaDTO()));
        when(service.get(1l)).thenReturn(getPessoaDTO());
        when(service.get(2l)).thenReturn(null);
        when(service.add(any(PessoaDTO.class))).thenReturn(getPessoaDTO());
        when(service.update(any(PessoaDTO.class))).thenReturn(getPessoaDTO());
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
    private String getPessoaAsJson() throws IOException {
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
