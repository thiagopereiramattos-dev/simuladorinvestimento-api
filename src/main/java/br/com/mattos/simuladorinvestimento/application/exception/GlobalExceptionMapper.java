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
 * Mapper global para capturar todas as exceções não tratadas na API.
 * <p>
 * Este mapper realiza:
 * <ul>
 *   <li>Tratamento de {@link NotFoundException}: retorna status 404 (Not Found) com mensagem padrão {@link ErrorType#RECURSO_NAO_ENCONTRADO}.</li>
 *   <li>Tratamento de exceções inesperadas: retorna status 500 (Internal Server Error) com mensagem padrão {@link ErrorType#ERRO_INTERNO}.</li>
 * </ul>
 * </p>
 * <p>
 * Todas as respostas seguem o padrão {@link ApiErrorResponse}.
 * </p>
 */
@Provider
public class GlobalExceptionMapper extends BaseExceptionHandler implements ExceptionMapper<Throwable> {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionMapper.class);

    @Context
    UriInfo uriInfo;

    @Override
    public Response toResponse(Throwable exception) {

        if (exception instanceof NotFoundException) {
            LOGGER.warn("Recurso não encontrado: {}", uriInfo.getPath());

            return buildResponse(Response.Status.NOT_FOUND,
                    ErrorType.RECURSO_NAO_ENCONTRADO.getMensagemPadrao(),
                    uriInfo);
        }

        LOGGER.error("Erro inesperado na API | Path: {}", uriInfo.getPath(), exception);

        return buildResponse(
                Response.Status.INTERNAL_SERVER_ERROR,
                ErrorType.ERRO_INTERNO.getMensagemPadrao(),
                uriInfo
        );
    }
}
