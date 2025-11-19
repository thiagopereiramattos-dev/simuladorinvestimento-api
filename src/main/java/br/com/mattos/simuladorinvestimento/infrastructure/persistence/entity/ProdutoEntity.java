package br.com.mattos.simuladorinvestimento.infrastructure.persistence.entity;

import br.com.mattos.simuladorinvestimento.domain.enums.RiscoProduto;
import jakarta.persistence.*;
import lombok.*;

/**
 * Entidade que representa um produto financeiro disponível para investimento.
 * <p>
 * Mapeada para a tabela {@code produto}, contém informações como nome, tipo,
 * rentabilidade, nível de risco e descrição. O tipo do produto é representado
 * por uma associação para {@link TipoProdutoEntity}, e o risco é armazenado
 * como enum em formato texto.
 * </p>
 */
@Entity
@Table(name = "produto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProdutoEntity {

    /**
     * Identificador da produto.
     * <p>
     * OBSERVAÇÃO: Está como {@link Integer} devido a erro do SQLite com Hibernate ORM para IDs gerados automaticamente (IDENTITY).
     * Tentar usar {@link Long} com @Id + @GeneratedValue causa problemas de DDL, fazendo com que a coluna perca o tipo no banco.
     * </p>
     * <p>
     * No domínio e DTOs, esse ID será representado como {@link Long} para permitir maior portabilidade e segurança futura.
     * </p>
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome_produto", nullable = false)
    private String nome;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_produto_id", nullable = false)
    private TipoProdutoEntity tipo;

    @Column(name = "taxa_rentabilidade", nullable = false)
    private Double rentabilidade;

    @Enumerated(EnumType.STRING)
    @Column(name = "risco", nullable = false)
    private RiscoProduto risco;

    @Column(name = "descricao")
    private String descricao;
}

