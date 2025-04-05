package SistemaVendas.SistemaVendas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import SistemaVendas.SistemaVendas.model.Cliente;
import SistemaVendas.SistemaVendas.model.Produto;
import SistemaVendas.SistemaVendas.repository.ClienteRepository;
import SistemaVendas.SistemaVendas.repository.ProdutoRepository;

@Service
public class CadastroService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;


    public void salvarCliente(Cliente cliente) {
        clienteRepository.save(cliente); // Salva o cliente no banco
    }

    public void salvarProduto(Produto produto) {
    	produtoRepository.save(produto);
    }

}
