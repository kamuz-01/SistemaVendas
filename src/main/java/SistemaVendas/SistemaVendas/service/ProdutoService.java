package SistemaVendas.SistemaVendas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import SistemaVendas.SistemaVendas.model.Produto;
import SistemaVendas.SistemaVendas.repository.ProdutoRepository;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public void salvarProduto(Produto produto) {
        produtoRepository.save(produto);
    }

    public Produto buscarPorId(Long id) {
        System.out.println("Buscando produto com ID: " + id);
        Produto produto = produtoRepository.findByIdWithVendasItens(id).orElse(null);
        if (produto != null) {
            System.out.println("Produto carregado: " + produto.getDescricaoProduto() + 
                ", VendasItens size: " + (produto.getVendasItens() != null ? produto.getVendasItens().size() : "null"));
        } else {
            System.out.println("Produto não encontrado para ID: " + id);
        }
        return produto;
    }

    public List<Produto> listarTodosProdutos() {
        return produtoRepository.findAll();
    }

    public void deletarProduto(Long id) {
        produtoRepository.deleteById(id);
    }
}