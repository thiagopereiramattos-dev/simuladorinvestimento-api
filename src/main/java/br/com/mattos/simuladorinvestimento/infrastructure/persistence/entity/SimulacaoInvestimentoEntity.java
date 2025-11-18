package br.com.mattos.simuladorinvestimento.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "simulacao_investimento")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SimulacaoInvestimentoEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

//  IMPORTANTE SOBRE O NOME DAS FK de PRODUTO E CLIENTE:
//  Em SQLite + Hibernate existe um bug específico na geração do DDL: Todas as colunas de relacionamento (@ManyToOne / @JoinColumn) são
//  ordenadas alfabeticamente ANTES da criação das demais colunas. Isso faz com que FKs venham antes do ID.
//  - Se o nome da FK vier alfabeticamente antes de "id", o Hibernate gera um DDL incorreto (ex.: coluna "id," sem tipo).
//  Para evitar esse bug, as FKs abixo são nomeadas com o padrão: id_<entidade>  Ex.: id_cliente, id_produto
//  O problema estava no cliente_id mas como ele foi alterado para id_cliente, o nome da coluna de produto tambem foi alterada para as
// duas ficarem usando o mesmo padrão de nome id_<entidade>

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_produto")
    private ProdutoEntity produto;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_cliente")
    private ClienteEntity cliente;

//    @Column(name = "id_produto", nullable = false)
//    private Long idProduto;
//
//    @Column(name = "id_cliente", nullable = false)
//    private Long idCliente;

    @Column(name = "valor_inicial9", nullable = false)
    private Double valorInicial;

    @Column(name = "valor_final", nullable = false)
    private Double valorFinal;

    @Column(name = "rentabilidade", nullable = false)
    private Double rentabilidade;

    @Column(name = "prazo", nullable = false)
    private Integer prazo;

    @Column(name = "data_simulacao", nullable = false)
    private Instant dataSimulacao;

    @Column(name = "data_simulacao_texto")
    private String dataSimulacaoTexto;

}