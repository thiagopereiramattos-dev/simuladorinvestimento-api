package br.com.mattos.simuladorinvestimento.application.exception;

import java.time.Instant;

public record ApiErrorResponse(int status,String error,String mensagem,String path,Instant timestamp) {

}
