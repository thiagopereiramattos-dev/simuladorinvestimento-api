package br.com.mattos.simuladorinvestimento.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

/**
 * Entidade que representa a telemetrica dos servicos.
 * <p>
 * Mapeada para a tabela {@code telemetria_servico}, armazena informações básicas
 * como nomeServico, tempoRespostaMs  e data.
 * </p>
 */
@Entity
@Table(name = "telemetria_servico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TelemetriaServicoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome_metodo_full", nullable = false)
    private String nomeMeotodoFull;

    @Column(name = "nome_metodo", nullable = false)
    private String nomeMetodo;

    @Column(name = "tempo_resposta_ms", nullable = false)
    private Integer tempoRespostaMs;

    @Column(name = "data", nullable = false)
    private LocalDate data;
}
