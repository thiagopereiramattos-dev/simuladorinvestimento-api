package br.com.mattos.simuladorinvestimento.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * Representa o tipo de um produto financeiro.
 * <p>
 * Esta entidade é mapeada para a tabela {@code tipo_produto} no banco de dados.
 * Contém informações como nome e descrição do tipo.
 * </p>
 */
@Entity
@Table(name = "tipo_produto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TipoProdutoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome", unique = true, nullable = false)
    private String nome;

    @Column(name = "descricao")
    private String descricao;
}
