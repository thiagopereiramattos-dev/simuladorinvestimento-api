package br.com.mattos.simuladorinvestimento.application.resource;

import br.com.mattos.simuladorinvestimento.application.dto.request.LoginRequestDTO;
import br.com.mattos.simuladorinvestimento.application.dto.response.LoginResponseDTO;
import br.com.mattos.simuladorinvestimento.application.mapper.AuthMapper;
import br.com.mattos.simuladorinvestimento.domain.exception.ClienteNaoEncontradoException;
import br.com.mattos.simuladorinvestimento.domain.exception.SenhaInvalidaException;
import br.com.mattos.simuladorinvestimento.domain.model.AuthResultado;
import br.com.mattos.simuladorinvestimento.domain.service.ClienteAuthService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthResourceTest {

    @InjectMocks
    AuthResource resource;

    @Mock
    ClienteAuthService authService;

    @Mock
    AuthMapper mapper;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    // ============================================================
    //  TESTE LOGIN COM SUCESSO
    // ============================================================
    @Test
    void deveAutenticarComSucesso() {
        LoginRequestDTO request = new LoginRequestDTO("teste@email.com", "123456");

        AuthResultado authResultado =
                new AuthResultado(1L, "token123");

        LoginResponseDTO responseDTO =
                new LoginResponseDTO("token123", 1L);

        when(authService.autenticar("teste@email.com", "123456"))
                .thenReturn(authResultado);

        when(mapper.toDTO(authResultado)).thenReturn(responseDTO);

        LoginResponseDTO result = resource.login(request);

        assertNotNull(result);
        assertEquals(1L, result.clientId());
        assertEquals("token123", result.token());

        verify(authService).autenticar("teste@email.com", "123456");
        verify(mapper).toDTO(authResultado);
    }

    // ============================================================
    //  TESTE EMAIL NÃO ENCONTRADO
    // ============================================================
    @Test
    void deveFalharQuandoEmailNaoExiste() {
        LoginRequestDTO request = new LoginRequestDTO("naoexiste@email.com", "123");

        when(authService.autenticar(request.email(), request.senha()))
                .thenThrow(new ClienteNaoEncontradoException("Não existe cliente com esse email"));

        assertThrows(ClienteNaoEncontradoException.class,
                () -> resource.login(request));

        verify(authService).autenticar(request.email(), request.senha());
        verify(mapper, never()).toDTO(any());
    }

    // ============================================================
    //  TESTE SENHA INVÁLIDA
    // ============================================================
    @Test
    void deveFalharQuandoSenhaInvalida() {
        LoginRequestDTO request = new LoginRequestDTO("cliente@email.com", "senhaerrada");

        when(authService.autenticar(request.email(), request.senha()))
                .thenThrow(new SenhaInvalidaException());

        assertThrows(SenhaInvalidaException.class,
                () -> resource.login(request));

        verify(authService).autenticar(request.email(), request.senha());
        verify(mapper, never()).toDTO(any());
    }

}
