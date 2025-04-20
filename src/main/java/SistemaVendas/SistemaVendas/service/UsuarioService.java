package SistemaVendas.SistemaVendas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import SistemaVendas.SistemaVendas.model.Usuario;
import SistemaVendas.SistemaVendas.repository.UsuarioRepository;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Método para retornar todos os usuários
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    // Método para salvar um usuário (caso necessário para outras funcionalidades)
    public void saveUsuario(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    // Método para buscar um usuário pelo ID (se necessário em outras funcionalidades)
    public Usuario getUsuarioById(int id) {
        return usuarioRepository.findById(id).orElse(null);
    }
}
