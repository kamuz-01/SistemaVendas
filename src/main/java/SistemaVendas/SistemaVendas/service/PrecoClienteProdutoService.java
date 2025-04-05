package SistemaVendas.SistemaVendas.service;

import SistemaVendas.SistemaVendas.model.PrecoClienteProduto;
import SistemaVendas.SistemaVendas.repository.PrecoClienteProdutoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrecoClienteProdutoService {

    @Autowired
    private PrecoClienteProdutoRepository precoClienteProdutoRepository;

    // Salvar ou atualizar o preço do cliente e produto
    public PrecoClienteProduto salvarPreco(PrecoClienteProduto precoClienteProduto) {
        return precoClienteProdutoRepository.save(precoClienteProduto);  // Agora o repositório tem o método save()
    }

    // Listar todos os preços
    public List<PrecoClienteProduto> listarPrecos() {
        return precoClienteProdutoRepository.findAll();
    }

    // Buscar preço por ID
    public Optional<PrecoClienteProduto> buscarPorId(Long id) {
        return precoClienteProdutoRepository.findById(id);
    }

    // Excluir preço
    public void excluirPreco(Long id) {
        precoClienteProdutoRepository.deleteById(id);
    }

    // Você pode adicionar lógica extra aqui para tratar alterações de preços, como notificação
}
