package br.com.mattos.simuladorinvestimento.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entidade que representa o tipo de um produto financeiro.
 * <p>
 * Mapeada para a tabela {@code tipo_produto}, armazena informações básicas
 * como nome (valor único) e descrição. É utilizada para categorizar os produtos na aplicação
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
