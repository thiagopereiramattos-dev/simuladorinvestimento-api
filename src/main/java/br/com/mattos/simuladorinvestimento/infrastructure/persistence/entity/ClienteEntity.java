package br.com.mattos.simuladorinvestimento.infrastructure.persistence.entity;

import br.com.mattos.simuladorinvestimento.domain.enums.PerfilRiscoCliente;
import jakarta.persistence.*;
import lombok.*;

/**
 * Representa um cliente do sistema de simulação de investimentos.
 * <p>
 * Esta entidade é mapeada para a tabela {@code cliente} no banco de dados.
 * Contém informações básicas do cliente, como nome, CPF, e-mail, perfil de risco e senha criptografada.
 * </p>
 */
@Entity
@Table(name = "cliente")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "cpf", nullable = false)
    private Long cpf;

    @Column(name = "email")
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "risco", nullable = false)
    private PerfilRiscoCliente perfilRisco;

    @Column(name = "senha_hash", nullable = false)
    private String senhaHash;
}

