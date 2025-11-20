package br.com.mattos.simuladorinvestimento.application.exception;

import com.fasterxml.jackson.core.JsonParseException;
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
 *
 * <p>Este mapper realiza:</p>
 * <ul>
 *   <li>Tratamento de JSON mal formatado ({@link JsonParseException}) embrulhado: retorna HTTP 400 (Bad Request) com mensagem detalhando o problema.</li>
 *   <li>Tratamento de quaisquer outras exceções inesperadas: retorna HTTP 500 (Internal Server Error) com mensagem padrão.</li>
 * </ul>
 *
 * <p>Todos os logs são registrados, e as respostas seguem o padrão {@link ApiErrorResponse} ou {@link ApiErrorResponseDet} quando apropriado.</p>
 *
 * <p>Essa abordagem garante que a API sempre retorne respostas consistentes ao cliente, evitando vazamento de stack traces ou informações internas.</p>
 */
@Provider
public class GlobalExceptionMapper extends BaseExceptionHandler implements ExceptionMapper<Throwable> {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionMapper.class);

    @Context
    UriInfo uriInfo;

    @Override
    public Response toResponse(Throwable exception) {

        String path = (uriInfo != null) ? uriInfo.getPath() : "desconhecido";

        // Tratar JsonParseException embrulhada
        Throwable causa = exception.getCause();
        if (causa instanceof JsonParseException jsonParse) {
            LOGGER.warn("JSON mal formatado na requisição | Path: {}", path, jsonParse);
            String mensagem = "JSON inválido: ";
            return buildResponse(Response.Status.BAD_REQUEST, mensagem, uriInfo);
        }

        LOGGER.error("Erro inesperado na API | Path: {}", path, exception);
        return buildResponse(
                Response.Status.INTERNAL_SERVER_ERROR,
                "Ocorreu um erro interno na aplicação.",
                uriInfo
        );
    }
}
