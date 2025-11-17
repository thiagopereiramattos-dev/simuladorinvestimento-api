package br.com.mattos.simuladorinvestimento.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "produto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProdutoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_produto", nullable = false)
    private String nome;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_produto_id", nullable = false)
    private TipoProdutoEntity tipo;

    @Column(name = "taxa_rentabilidade", nullable = false)
    private Double rentabilidade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "risco_id", nullable = false)
    private RiscoEntity risco;

    @Column(name = "descricao")
    private String descricao;
}

