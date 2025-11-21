package br.com.mattos.simuladorinvestimento.domain.service;

import br.com.mattos.simuladorinvestimento.domain.exception.ClienteNaoEncontradoException;
import br.com.mattos.simuladorinvestimento.domain.model.ClientePerfilRisco;
import br.com.mattos.simuladorinvestimento.domain.repository.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {

    @Mock
    ClienteRepository clienteRepository;

    @InjectMocks
    ClienteService clienteService;

    @Test
    void deveRetornarPerfilQuandoClienteExiste() {
        ClientePerfilRisco perfil = new ClientePerfilRisco(1L, "Conservador", 10, "Perfil seguro");
        when(clienteRepository.buscarPerfilRiscoPorId(1L)).thenReturn(Optional.of(perfil));

        ClientePerfilRisco resultado = clienteService.consultarPerfilRiscoCliente(1L);

        assertEquals(perfil, resultado);
        verify(clienteRepository).buscarPerfilRiscoPorId(1L);
    }

    @Test
    void deveLancarExcecaoQuandoClienteNaoExiste() {
        when(clienteRepository.buscarPerfilRiscoPorId(2L)).thenReturn(Optional.empty());

        assertThrows(ClienteNaoEncontradoException.class, () ->
                clienteService.consultarPerfilRiscoCliente(2L)
        );

        verify(clienteRepository).buscarPerfilRiscoPorId(2L);
    }
}
