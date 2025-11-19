package br.com.mattos.simuladorinvestimento.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entidade que representa um perfil de risco do cliente.
 * <p>
 * Armazena o nome do perfil, pontuação e descrição.
 * </p>
 */
@Entity
@Table(name = "perfil_risco")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PerfilRiscoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome", nullable = false, unique = true)
    private String nome;

    @Column(name = "pontuacao", nullable = false)
    private Integer pontuacao;

    @Column(name = "descricao", nullable = false)
    private String descricao;
}
