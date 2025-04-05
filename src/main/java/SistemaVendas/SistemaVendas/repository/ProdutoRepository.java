package SistemaVendas.SistemaVendas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import SistemaVendas.SistemaVendas.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	Produto findByIdProduto(Long idProduto);

	Produto findBySku(String sku);
	
	@Query("SELECT p FROM Produto p LEFT JOIN FETCH p.vendasItens WHERE p.idProduto = :id")
    Optional<Produto> findByIdWithVendasItens(@Param("id") Long id);
}
