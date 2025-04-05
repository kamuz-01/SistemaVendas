package SistemaVendas.SistemaVendas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import SistemaVendas.SistemaVendas.model.Venda;

public interface VendaRepository extends JpaRepository<Venda, Long> {

	//List<Venda> findByClienteIdCliente(Long idCliente);
    // JpaRepository já fornece os métodos básicos de CRUD
	
	@Query("SELECT v FROM Venda v WHERE v.cliente.idCliente = :idCliente")
    List<Venda> findByClienteIdCliente(@Param("idCliente") Long idCliente);
	
	@Query("SELECT v FROM Venda v JOIN FETCH v.itens WHERE v.idVenda = :idVenda")
    Venda findByIdWithItens(@Param("idVenda") Long idVenda);
}