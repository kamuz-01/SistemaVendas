package SistemaVendas.SistemaVendas.service;

import SistemaVendas.SistemaVendas.model.Cliente;
import SistemaVendas.SistemaVendas.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    // Listar todos os clientes
    public List<Cliente> listarClientes() {
        return clienteRepository.findAll(); // Retorna todos os clientes
    }

    // Buscar cliente por ID
    public Cliente buscarPorId(Long id) {
        return clienteRepository.findById(id).orElse(null); // Busca cliente por ID
    }

    // Salvar cliente
    public Cliente salvarCliente(Cliente cliente) {
        return clienteRepository.save(cliente); // Salva ou atualiza o cliente
    }

    // Excluir cliente
    public void excluirCliente(Long id) {
        clienteRepository.deleteById(id); // Exclui o cliente pelo ID
    }
}
