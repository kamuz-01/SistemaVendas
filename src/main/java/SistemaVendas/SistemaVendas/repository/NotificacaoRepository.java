package SistemaVendas.SistemaVendas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import SistemaVendas.SistemaVendas.model.Notificacao;

public interface NotificacaoRepository extends JpaRepository<Notificacao, Long> {
	
}