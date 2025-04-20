package SistemaVendas.SistemaVendas.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import jakarta.servlet.http.HttpSession;

@ControllerAdvice
public class GlobalControllerAdvice {

    // Adiciona o nome do usuário e o nível de acesso ao modelo de todas as páginas
    @ModelAttribute
    public void addAttributesToModel(Model model, HttpSession session) {
        String usuario = (String) session.getAttribute("usuario");
        String nivelAcesso = (String) session.getAttribute("nivelAcesso");
        Long idUsuario = (Long) session.getAttribute("idUsuario");

        if (usuario != null && nivelAcesso != null) {
            model.addAttribute("usuario", usuario); // Passa o nome do usuário
            model.addAttribute("nivelAcesso", nivelAcesso); // Passa o nível de acesso
            model.addAttribute("idUsuario", idUsuario);
        }
    }
}