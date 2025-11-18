package br.com.mattos.simuladorinvestimento.application.exception;

import java.util.List;

public record ApiErrorResponseDet(
        int status,
        String error,
        String mensagem,
        String path,
        List<CampoErro> detalhes
) {}
