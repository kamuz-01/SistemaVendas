package SistemaVendas.SistemaVendas.repository;

import SistemaVendas.SistemaVendas.model.PrecoClienteProduto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrecoClienteProdutoRepository extends JpaRepository<PrecoClienteProduto, Long> {
	
	List<PrecoClienteProduto> findByProduto_IdProduto(Long idProduto);
}
