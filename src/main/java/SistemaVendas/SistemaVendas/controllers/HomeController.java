package SistemaVendas.SistemaVendas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import jakarta.servlet.http.HttpSession;
import SistemaVendas.SistemaVendas.model.Usuario;
import SistemaVendas.SistemaVendas.service.AutenticacaoService;
import SistemaVendas.SistemaVendas.repository.UsuarioRepository;

@Controller
public class HomeController {

    @Autowired
    private AutenticacaoService autenticacaoService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Processamento do login
    @PostMapping("/login")
    public String processaLogin(@RequestParam String nomeLogin, @RequestParam String senha, HttpSession session, Model model) {
        // Chama o serviço para autenticar o usuário e obter o nível de acesso
        String nivelAcesso = autenticacaoService.autenticarUsuario(nomeLogin, senha);

        if (nivelAcesso != null) {
            // Após autenticar, busca o usuário completo do repositório
            Usuario usuario = usuarioRepository.findByNomeLogin(nomeLogin)
                    .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

            // Armazena o nome do usuário, o nível de acesso e o id na sessão
            session.setAttribute("usuario", usuario.getNomeUsuario());
            session.setAttribute("nivelAcesso", nivelAcesso);
            session.setAttribute("idUsuario", usuario.getIdUsuario());

            // Verifica o nível de acesso do usuário e redireciona conforme o caso
            if ("Atendente".equalsIgnoreCase(nivelAcesso)) {
                return "redirect:/atendente"; // Redireciona para a página do atendente
            } else if ("Administrador".equalsIgnoreCase(nivelAcesso)) {
                return "redirect:/administracao"; // Redireciona para a página de administração
            }
        } else {
            // Se as credenciais forem inválidas, passa um erro para a página de login
            model.addAttribute("erro", "Usuário ou senha inválidos.");
            return "login";  // O nome do seu arquivo de login, sem a extensão .html
        }
        return nivelAcesso;
    }

    // Exibindo o formulário de login
    @GetMapping("/login")
    public String loginForm() {
        return "login"; // Página de login
    }

    // Exibe a página de administração com o nome do usuário e nível de acesso
    @GetMapping("/administracao")
    public String adminPage(Model model, HttpSession session) {
        // Recupera o nome do usuário e o nível de acesso da sessão
        String usuario = (String) session.getAttribute("usuario");
        String nivelAcesso = (String) session.getAttribute("nivelAcesso");

        if (usuario != null && nivelAcesso != null) {
            model.addAttribute("usuario", usuario); // Passa o nome do usuário para o modelo
            model.addAttribute("nivelAcesso", nivelAcesso); // Passa o nível de acesso para o modelo
            return "administracao"; // Retorna a página de administração
        } else {
            return "redirect:/login"; // Se o usuário não estiver logado, redireciona para o login
        }
    }

    // Exibe a página do atendente com o nome do usuário e nível de acesso
    @GetMapping("/atendente")
    public String atendentePage(Model model, HttpSession session) {
        // Recupera o nome do usuário e o nível de acesso da sessão
        String usuario = (String) session.getAttribute("usuario");
        String nivelAcesso = (String) session.getAttribute("nivelAcesso");

        if (usuario != null && nivelAcesso != null && "Atendente".equalsIgnoreCase(nivelAcesso)) {
            model.addAttribute("usuario", usuario); // Passa o nome do usuário para o modelo
            model.addAttribute("nivelAcesso", nivelAcesso); // Passa o nível de acesso para o modelo
            return "atendente"; // Retorna a página de atendente
        } else {
            return "redirect:/login"; // Se o usuário não for um atendente ou não estiver logado, redireciona para o login
        }
    }

    // Método para realizar o logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // Remove os dados de usuário e nível de acesso da sessão
        session.invalidate(); // Invalida a sessão completamente, removendo todos os atributos
        return "redirect:/login"; // Redireciona para a página de login
    }
}
