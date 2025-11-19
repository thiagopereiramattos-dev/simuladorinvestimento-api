package br.com.mattos.simuladorinvestimento.infrastructure.persistence.entity;

import br.com.mattos.simuladorinvestimento.domain.enums.PerfilRiscoCliente;
import jakarta.persistence.*;
import lombok.*;

/**
 * Entidade que representa um cliente do sistema de simulação de investimentos.
 * <p>
 * Mapeada para a tabela {@code cliente}, armazena dados como nome, CPF,
 * e-mail, perfil de risco (via enum {@link PerfilRiscoCliente}) e o hash da senha do usuário.
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

    /**
     * Identificador da cliente.
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

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "cpf", nullable = false)
    private String cpf;

    @Column(name = "email")
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "risco", nullable = false)
    private PerfilRiscoCliente perfilRisco;

    @Column(name = "senha_hash", nullable = false)
    private String senhaHash;
}

