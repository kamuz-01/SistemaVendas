package SistemaVendas.SistemaVendas.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import SistemaVendas.SistemaVendas.model.Venda;
import SistemaVendas.SistemaVendas.model.VendaItem;
public interface VendasItensRepository extends JpaRepository<VendaItem, Integer> {

	@Query("SELECT vi FROM VendaItem vi WHERE vi.venda.idVenda = :idVenda")
	List<VendaItem> findByVendaId(@Param("idVenda") Long idVenda);
	
	public List<VendaItem> findByVenda(Venda venda);

}
