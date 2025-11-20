package br.com.mattos.simuladorinvestimento.domain.enums;

import br.com.mattos.simuladorinvestimento.domain.exception.PerfilInvalidoException;

import java.text.Normalizer;
import java.util.Locale;
import java.util.Optional;

public enum RiscoProduto {
    BAIXO("Baixo"),
    MEDIO("Médio"),
    ALTO("Alto");

    private final String descricao;

    RiscoProduto(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    /**
     * Converte uma string em RiscoProduto ignorando case e acentos.
     *
     * @param valor string do perfil (ex: "Medio", "Médio")
     * @return enum correspondente
     * @throws PerfilInvalidoException se não for válido
     */
    public static RiscoProduto fromString(String valor) {
        if (valor == null || valor.isBlank()) {
            throw new PerfilInvalidoException(valor);
        }

        String normalizado = Normalizer
                .normalize(valor, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "") // remove acentos
                .toUpperCase(Locale.ROOT)
                .trim();

        return Optional.ofNullable(
                switch (normalizado) {
                    case "BAIXO" -> BAIXO;
                    case "MEDIO", "MÉDIO" -> MEDIO;
                    case "ALTO" -> ALTO;
                    default -> null;
                }
        ).orElseThrow(() -> new PerfilInvalidoException(valor));
    }
}
