package SistemaVendas.SistemaVendas.repository;

import SistemaVendas.SistemaVendas.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByNomeLogin(String nomeLogin);

    Optional<Usuario> findByIdUsuario(Long idUsuario);
}