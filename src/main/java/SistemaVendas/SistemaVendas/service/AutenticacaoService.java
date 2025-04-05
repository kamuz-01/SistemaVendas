package SistemaVendas.SistemaVendas.service;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import SistemaVendas.SistemaVendas.model.Usuario;
import SistemaVendas.SistemaVendas.repository.UsuarioRepository;

import java.util.Optional;

@Service
public class AutenticacaoService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Método para autenticar o usuário e verificar o nível de acesso.
     *
     * @param nomeLogin Nome de login do usuário.
     * @param senha Senha fornecida pelo usuário.
     * @return Nível de acesso do usuário ou null se a autenticação falhar.
     */
    public String autenticarUsuario(String nomeLogin, String senha) {
        // Consultar o usuário pelo nomeLogin
        Optional<Usuario> usuarioOpt = usuarioRepository.findByNomeLogin(nomeLogin);

        // Verifica se o usuário foi encontrado
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();

            // Verifica se a senha fornecida é válida
            if (BCrypt.checkpw(senha, usuario.getSenha())) {
                // Se a senha for correta, retorna o nível de acesso
                return usuario.getNivelAcesso();
            }
        }

        // Se não encontrar o usuário ou a senha for incorreta, retorna null
        return null;
    }
}
