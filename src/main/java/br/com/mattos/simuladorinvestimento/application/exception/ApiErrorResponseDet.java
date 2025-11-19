package br.com.mattos.simuladorinvestimento.application.exception;

import jakarta.validation.ConstraintViolationException;

import java.util.List;

/**
 * Representa a estrutura de resposta de erro da API para exceções que contêm múltiplos detalhes de campos inválidos.
 * @param status Código HTTP da resposta
 * @param error Descrição do status HTTP
 * @param mensagem Mensagem geral do erro
 * @param path Caminho da requisição que causou o erro
 * @param detalhes Lista de {@link CampoErro} detalhando cada campo inválido
 */
public record ApiErrorResponseDet(
        int status,
        String error,
        String mensagem,
        String path,
        List<CampoErro> detalhes
) {}
