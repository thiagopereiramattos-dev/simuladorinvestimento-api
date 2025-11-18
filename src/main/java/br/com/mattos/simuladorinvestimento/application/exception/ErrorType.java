package br.com.mattos.simuladorinvestimento.application.exception;

/**
 * Enumeração que define os tipos de erro padronizados na API.
 * Cada tipo possui uma mensagem padrão que pode ser utilizada nas respostas de erro.
 */
public enum ErrorType {

    VALIDACAO("Erro de validação nos campos da requisição."),
    RECURSO_NAO_ENCONTRADO("Recurso não encontrado."),
    PRODUTO_NAO_ENCONTRADO("Produto informado não existe."),
    ERRO_INTERNO("Ocorreu um erro inesperado no servidor.");

    private final String mensagemPadrao;

    ErrorType(String mensagemPadrao) {
        this.mensagemPadrao = mensagemPadrao;
    }

    public String getMensagemPadrao() {
        return mensagemPadrao;
    }
}
