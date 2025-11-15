package br.com.mattos.simuladorinvestimento.infrastructure.persistence.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "produto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(
        name = "produtoSeq",
        sequenceName = "produto_seq",
        initialValue = 1,
        allocationSize = 1
)
public class ProdutoEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "produtoSeq")
    @Column(name = "id")
    private Long id;

    @Column(name = "nome_produto")
    private String nome;

    @Column(name = "tipo_produto")
    private String tipo;

    @Column(name = "taxa_rentabilidade")
    private Double rentabilidade;

    @Column(name = "nivel_risco")
    private String risco;
}
