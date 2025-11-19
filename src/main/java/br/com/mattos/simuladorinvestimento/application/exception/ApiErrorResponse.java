package br.com.mattos.simuladorinvestimento.application.exception;

import java.time.Instant;


/**
 * Representa a estrutura de resposta de erro da API para exceções simples.
 * Utilizado para erros que não precisam detalhar múltiplos campos (ex.: 404 Not Found, 500 Internal Server Error).
 * @param status Código HTTP da resposta
 * @param error Descrição do status HTTP
 * @param mensagem Mensagem detalhando o erro
 * @param path Caminho da requisição que causou o erro
 * @param timestamp Momento em que o erro ocorreu
 */
public record ApiErrorResponse(
        int status,
        String error,
        String mensagem,
        String path,
        Instant timestamp
) {}
