package br.com.mattos.simuladorinvestimento.application.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.time.Instant;

public abstract class BaseExceptionHandler {

    protected Response buildResponse(Response.Status status, String mensagem, UriInfo uriInfo) {
        ApiErrorResponse error = new ApiErrorResponse(
                status.getStatusCode(),
                status.getReasonPhrase(),
                mensagem,
                uriInfo.getPath(),
                Instant.now()
        );
        return Response.status(status).entity(error).build();
    }
}
