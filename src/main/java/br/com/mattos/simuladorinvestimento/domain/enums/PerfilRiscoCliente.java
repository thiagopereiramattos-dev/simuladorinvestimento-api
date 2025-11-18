package br.com.mattos.simuladorinvestimento.domain.enums;

public enum PerfilRiscoCliente {
    CONSERVADOR("Conservador"),
    MODERADO("Moderado"),
    AGRESSIVO("Agressivo");

    private final String label;

    PerfilRiscoCliente(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}