package br.com.mattos.simuladorinvestimento.application.exception;

import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import jakarta.ws.rs.core.UriInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Mapper para capturar exceções do tipo {@link NotFoundException}.
 * <p>
 * Utilizado para rotas inexistentes ou recursos não encontrados.
 * Retorna status HTTP 404 com resposta padronizada {@link ApiErrorResponse}.
 * </p>
 */
@Provider
public class NotFoundExceptionMapper extends BaseExceptionHandler implements ExceptionMapper<NotFoundException> {

    private static final Logger LOGGER = LoggerFactory.getLogger(NotFoundExceptionMapper.class);

    @Context
    UriInfo uriInfo;

    @Override
    public Response toResponse(NotFoundException exception) {
        String path = (uriInfo != null) ? uriInfo.getPath() : "desconhecido";

        LOGGER.warn("Recurso não encontrado: {}", path);

        return buildResponse(
                Response.Status.NOT_FOUND,
                "Recurso não encontrado",
                uriInfo
        );
    }
}
