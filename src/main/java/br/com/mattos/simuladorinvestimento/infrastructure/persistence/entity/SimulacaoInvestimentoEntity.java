package br.com.mattos.simuladorinvestimento.infrastructure.persistence.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
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
public class SimulacaoInvestimentoEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "INTEGER PRIMARY KEY AUTOINCREMENT")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "produto_id")
    private ProdutoEntity produto;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cliente_id")
    private ClienteEntity cliente;

    @Column(name = "valor_inicial", nullable = false)
    private Double valorInicial;

    @Column(name = "valor_final", nullable = false)
    private Double valorFinal;

    @Column(name = "rentabilidade", nullable = false)
    private Double rentabilidade;

    @Column(name = "prazo", nullable = false)
    private Integer prazo;

    @Column(name = "data_simulacao", nullable = false)
    private Instant dataSimulacao;
}
