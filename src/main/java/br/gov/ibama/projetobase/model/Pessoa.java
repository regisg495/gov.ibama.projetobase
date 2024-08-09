package br.gov.ibama.projetobase.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pessoa")
public class Pessoa {

    @Id
    @Column(name = "id_pessoa")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nu_cpf")
    private String cpf;

    @Column(name = "gen_pessoa")
    private char genero;

}
