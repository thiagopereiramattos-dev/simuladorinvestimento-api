package br.com.mattos.simuladorinvestimento.application.exception;

/**
 * Representa um detalhe de erro em um campo específico de uma requisição.
 *
 * @param campo   Nome do campo que causou o erro.
 * @param mensagem Mensagem descritiva do erro.
 */
public record CampoErro(
        String campo,
        String mensagem
) {}
