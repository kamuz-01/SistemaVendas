package SistemaVendas.SistemaVendas.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import SistemaVendas.SistemaVendas.model.Cliente;
import SistemaVendas.SistemaVendas.model.ClienteDTO;
import SistemaVendas.SistemaVendas.repository.ClienteRepository;

@RestController
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    // Endpoint para retornar todos os clientes no formato DTO
    @GetMapping("/api/clientes")
    public List<ClienteDTO> listarClientes() {
        List<Cliente> clientes = clienteRepository.findAll();
        // Convertendo para DTO para evitar o relacionamento com o Usuario
        return clientes.stream()
                       .map(cliente -> new ClienteDTO(cliente.getIdCliente(), 
                                                      cliente.getRazaosocialCliente(), 
                                                      cliente.getCnpjCliente()))
                       .collect(Collectors.toList());
    }
}
