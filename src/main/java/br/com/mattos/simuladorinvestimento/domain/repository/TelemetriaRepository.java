package br.com.mattos.simuladorinvestimento.domain.repository;

import br.com.mattos.simuladorinvestimento.domain.model.Cliente;
import br.com.mattos.simuladorinvestimento.domain.model.ClienteLogin;
import br.com.mattos.simuladorinvestimento.domain.model.ClientePerfilRisco;
import br.com.mattos.simuladorinvestimento.domain.model.Produto;

import java.util.List;
import java.util.Optional;

/** Interface para operações de persistência e consulta de produtos. */
public interface ClienteRepository {

    /** Busca um cliente pelo seu id.
     * @param idCliente id do cliente
     * @return um Optional com o cliente, ou vazio se não encontrado
     */
    Optional<Cliente> buscarPorId(Long idCliente);

    /** Lista todos os clientes disponíveis. */
    List<Cliente> listarTodos();

    Optional<ClientePerfilRisco> buscarPerfilRiscoPorId(Long idCliente);

    Optional<ClienteLogin> findByEmail(String email);

}
