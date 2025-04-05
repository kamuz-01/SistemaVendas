package SistemaVendas.SistemaVendas.repository;

import SistemaVendas.SistemaVendas.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    // Método para buscar clientes por razão social
    List<Cliente> findByRazaosocialCliente(String razaosocialCliente);

	Cliente findByCnpjCliente(String cnpjCliente);

}