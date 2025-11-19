package br.com.mattos.simuladorinvestimento.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.time.LocalDate;


/**
 * Representa uma simulação de investimento do sistema.
 * <p>
 * Esta entidade é mapeada para a tabela {@code simulacao_investimento} no banco de dados.
 * Contém informações sobre o cliente, o produto investido, valores iniciais e finais,
 * rentabilidade, prazo da simulação e datas correspondentes.
 * </p>
 */
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

    /**
     * Produto que realizou a simulação.
     *
     * IMPORTANTE: Nome da FK segue o padrão id_<entidade> por causa de um bug no SQLite + Hibernate:
     * Todas as colunas de relacionamento (@ManyToOne / @JoinColumn) são ordenadas alfabeticamente ANTES da criação das demais colunas.
     * Isso faz com que FKs venham antes do ID. Se o nome da FK vier alfabeticamente antes de "id", o Hibernate gera um DDL incorreto (ex.: coluna "id," sem tipo).
     * Para evitar esse bug, as FKs dessa entidade estão sendo nomeadas com o padrão: id_<entidade>  id_produto
     */

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_produto")
    private ProdutoEntity produto;

    /**
     * Cliente relacionado à simulação.
     *
     * IMPORTANTE: Nome da FK segue o padrão id_<entidade> por causa de um bug no SQLite + Hibernate:
     * Todas as colunas de relacionamento (@ManyToOne / @JoinColumn) são ordenadas alfabeticamente ANTES da criação das demais colunas.
     * Isso faz com que FKs venham antes do ID. Se o nome da FK vier alfabeticamente antes de "id", o Hibernate gera um DDL incorreto (ex.: coluna "id," sem tipo).
     * Para evitar esse bug, as FKs dessa entidade estão sendo nomeadas com o padrão: id_<entidade>  id_cliente
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_cliente")
    private ClienteEntity cliente;


    @Column(name = "valor_investido", nullable = false)
    private Double valorInvestido;

    @Column(name = "valor_final", nullable = false)
    private Double valorFinal;

    @Column(name = "rentabilidade", nullable = false)
    private Double rentabilidade;

    @Column(name = "prazo", nullable = false)
    private Integer prazo;

    /**
     * Data/hora completa da simulação (Instant).
     * Persistida como epochMilli mediante o AttributeConverter.
     */
    @Column(name = "data_simulacao", nullable = false)
    private Instant dataSimulacao;

    /**
     * Texto formatado da data da simulação (ex.: 2025-11-18T10:35:22).
     */
    @Column(name = "data_simulacao_texto")
    private String dataSimulacaoTexto;

    /**
     * Data da simulação preparada para relatórios agregados.
     */
    @Column(name = "data_simulacao_date", nullable = false)
    private LocalDate dataSimulacaoDate;


}