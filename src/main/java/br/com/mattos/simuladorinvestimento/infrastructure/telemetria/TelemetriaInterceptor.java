package br.com.mattos.simuladorinvestimento.infrastructure.telemetria;

import br.com.mattos.simuladorinvestimento.domain.service.TelemetriaService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;

import java.time.LocalDate;

/**
 * Interceptor para registrar telemetria de métodos anotados com {@link TelemetriaMonitor}.
 *
 * Agora delega a persistência ao {@link TelemetriaService} para garantir transação ativa.
 */
@Interceptor
@TelemetriaMonitor
public class TelemetriaInterceptor {

    @Inject
    TelemetriaService telemetriaService;

    @AroundInvoke
    public Object registrarTelemetria(InvocationContext ctx) throws Exception {
        long start = System.currentTimeMillis();
        try {
            return ctx.proceed();
        } finally {
            long tempo = System.currentTimeMillis() - start;

            telemetriaService.salvar(
                    ctx.getMethod().getDeclaringClass().getSimpleName() + "." + ctx.getMethod().getName(),
                    ctx.getMethod().getName(),
                    (int) tempo,
                    LocalDate.now()
            );
        }
    }
}
