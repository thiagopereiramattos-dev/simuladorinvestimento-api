package br.com.mattos.simuladorinvestimento.domain.exception;

public class PerfilInvalidoException extends DomainException {

    /**
     * @param nomePerfil mensagem explicando por que o produto é inválido
     */
    public PerfilInvalidoException(String nomePerfil) {
        super("Peril Invalido: '" + nomePerfil + "' para esta consulta informar Baixo , Médio ou  Alto.");
    }
}