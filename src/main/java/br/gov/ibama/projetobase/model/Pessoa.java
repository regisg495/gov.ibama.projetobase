package br.gov.ibama.projetobase.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "tb_pessoa")
public class Pessoa {

    @Id
    @Column(name = "id_pessoa")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "ds_cpf")
    private String cpf;

    @Column(name = "ds_genero")
    private char genero;

}
