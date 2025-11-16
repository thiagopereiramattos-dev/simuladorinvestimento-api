package br.com.mattos.simuladorinvestimento.infrastructure.persistence.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "produto")
public class ProdutoEntity extends PanacheEntity {

    @Column(name = "nome_produto", nullable = false)
    public String nome;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_produto_id", nullable = false)
    public TipoProdutoEntity tipo;

    @Column(name = "taxa_rentabilidade", nullable = false)
    public Double rentabilidade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "risco_id", nullable = false)
    public RiscoEntity risco;

    @Column(name = "descricao")
    public String descricao;
}
