package br.com.mattos.simuladorinvestimento.application.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.time.Instant;

/**
 * Classe base para ExceptionMappers da API, fornecendo utilitários comuns para construir respostas de erro.
 */
public abstract class BaseExceptionHandler {

    /**
     * Constrói a resposta de erro padrão com base no status HTTP, mensagem e URI da requisição.
     *
     * @param status   Status HTTP da resposta.
     * @param mensagem Mensagem de erro que será enviada ao cliente.
     * @param uriInfo  Informações da URI da requisição.
     * @return Objeto Response pronto para ser retornado pelo mapper.
     */
    protected Response buildResponse(Response.Status status, String mensagem, UriInfo uriInfo) {
        String path = (uriInfo != null) ? uriInfo.getPath() : "desconhecido";
        ApiErrorResponse error = new ApiErrorResponse(
                status.getStatusCode(),
                status.getReasonPhrase(),
                mensagem,
                path,
                Instant.now()
        );
        return Response.status(status).entity(error).build();
    }
}
