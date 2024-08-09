package br.gov.ibama.projetobase.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PessoaDTO {

    private Long id;

    private String cpf;

    private char genero;

}
