package SistemaVendas.SistemaVendas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import SistemaVendas.SistemaVendas.model.Venda;
import SistemaVendas.SistemaVendas.repository.VendaRepository;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;

    // Método para salvar uma nova venda
    public void salvarVenda(Venda venda) {
        vendaRepository.save(venda);  // Salva a venda no banco de dados
    }

    // Método para buscar uma venda pelo ID
    public Venda buscarVendaPorId(Long id) {
        return vendaRepository.findById(id).orElse(null);  // Retorna a venda com o ID especificado
    }
}
