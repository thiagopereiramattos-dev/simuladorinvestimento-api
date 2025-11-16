package br.com.mattos.simuladorinvestimento.application.exception;

import br.com.mattos.simuladorinvestimento.domain.exception.ProdutoNaoEncontradoException;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import jakarta.ws.rs.core.UriInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.Instant;

@Provider
public class GlobalExceptionMapper implements ExceptionMapper<Throwable> {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionMapper.class);

    @Context
    UriInfo uriInfo;

    @Override
    public Response toResponse(Throwable exception) {

        Response.Status status;
        String mensagem;

        if (exception instanceof ProdutoNaoEncontradoException) {
            status = Response.Status.NOT_FOUND;
            mensagem = exception.getMessage();
            LOGGER.info("Produto não encontrado: {} | Path: {}", mensagem, uriInfo.getPath());

        } else if (exception instanceof NotFoundException) {
            status = Response.Status.NOT_FOUND;
            mensagem = "Recurso não encontrado";

            String path = uriInfo.getPath();
            if ("/favicon.ico".equals(path)) {
                LOGGER.info("404 gerado para path automático: {}", path);
            } else {
                LOGGER.warn("404 inesperado gerado para path: {}", path);
            }

        } else {
            status = Response.Status.INTERNAL_SERVER_ERROR;
            mensagem = "Ocorreu um erro inesperado no servidor.";
            LOGGER.error("Erro inesperado na API | Path: {}", uriInfo.getPath(), exception);
        }

        ApiErrorResponse errorResponse = new ApiErrorResponse(
                status.getStatusCode(),
                status.getReasonPhrase(),
                mensagem,
                uriInfo.getPath(),
                Instant.now()
        );

        return Response.status(status)
                .entity(errorResponse)
                .build();
    }
}
